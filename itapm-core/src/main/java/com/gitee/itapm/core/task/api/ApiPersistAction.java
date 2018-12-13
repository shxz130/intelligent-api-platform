package com.gitee.itapm.core.task.api;

import com.gitee.itapm.mapper.InterfaceDetailDOMapper;
import com.gitee.itapm.mapper.bean.InterfaceCatagoryDO;
import com.gitee.itapm.paser.bean.*;
import com.gitee.itapm.service.InterfaceCatagoryService;
import com.gitee.itapm.service.SystemInfoService;
import com.gitee.itapm.service.bean.*;
import com.gitee.itapm.service.bus.*;
import com.gitee.itapm.service.impl.InterfaceCatagoryServiceImpl;
import com.sun.xml.internal.ws.wsdl.writer.document.ParamType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jetty on 2018/12/11.
 */
@Component
public class ApiPersistAction {

    @Autowired
    private SystemInfoBusService systemInfoBusService;
    @Autowired
    private InterfaceCatagoryBusService interfaceCatagoryBusService;
    @Autowired
    private SystemVersionBusService systemVersionBusService;
    @Autowired
    private ParamTypeBusService paramTypeBusService;
    @Autowired
    private InterfaceDetailBusService interfaceDetailBusService;
    @Autowired
    private ParamFieldBusService paramFieldBusService;
    @Autowired
    private ParamGenericTypeBusService paramGenericTypeBusService;
    @Autowired
    private ParamTypeRefGenericBusService paramTypeRefGenericBusService;

    public void handle(Document document){
        //初始化系统信息 增加，修改
        SystemInfoBO systemInfoBO=systemInfoBusService.persist(document.getSystemEnName(), document.getSystemChName());
        //初始化版本信息
        SystemVersionBO systemVersionBO=systemVersionBusService.persist(systemInfoBO, document.getVersion());

        List<InterfaceCatagoryBO> catagoryList=interfaceCatagoryBusService.queryBySystemVersionId(systemVersionBO.getId());

        for(Catagory catagory:document.getCatagoryList()){
            //接口分类处理
            InterfaceCatagoryBO interfaceCatagoryBO= interfaceCatagoryBusService.persist(systemVersionBO.getId(), catagory.getCatagoryName());
            //接口文档处理
            handleApiDoc(systemVersionBO, interfaceCatagoryBO, catagory.getApiDocList());

            catagoryList.remove(catagory);
        }
        //删除系统没有的分类
        for(InterfaceCatagoryBO interfaceCatagoryBO:catagoryList){

            interfaceCatagoryBusService.deleteById(interfaceCatagoryBO.getId());

        }

    }

    private void handleApiDoc(SystemVersionBO systemVersionBO,InterfaceCatagoryBO interfaceCatagoryBO,List<ApiDoc> apiDocList){
        if(CollectionUtils.isEmpty(apiDocList)){
            return;
        }
        //查询数据库所有该分类的接口信息
        List<InterfaceDetailBO> currentSystemInterfaceDetailList=interfaceDetailBusService.queryByCatagoryId(interfaceCatagoryBO.getId());

        for(ApiDoc apiDoc:apiDocList){
            //处理泛型
            handleGeneric(systemVersionBO,apiDoc,apiDoc.getGenericParameterList());
            //接口明细落库 插入，修改
            InterfaceDetailBO interfaceDetailBO=interfaceDetailBusService.persist(convertApiDoc2InterfaceDetail(systemVersionBO, interfaceCatagoryBO, apiDoc));
            //参数类型处理
            handleParamType(interfaceDetailBO, apiDoc);
            //
            currentSystemInterfaceDetailList.remove(interfaceDetailBO);
        }
        //删除数据库老的接口信息（注释去掉的哪些）
        for(InterfaceDetailBO interfaceDetailBO:currentSystemInterfaceDetailList){
            interfaceDetailBusService.deleteById(interfaceDetailBO.getId());
        }
    }

    private void handleGeneric(SystemVersionBO systemVersionBO,ApiDoc apiDoc,List<Parameter> parameterList){

        if(CollectionUtils.isEmpty(parameterList)){
           return;
        }
        for(Parameter parameter:parameterList){
            //保存
            ParamGenericTypeBO paramGenericType=paramGenericTypeBusService.persist(systemVersionBO.getId(), parameter.getName());
            //查询数据库所有记录
            List<ParamFieldBO> paramFieldDBBOList=paramFieldBusService.queryByParamTypeId(paramGenericType.getId());
            //所有数据落库
            if(!CollectionUtils.isEmpty(parameter.getParamFieldList())){
                for(ParamField paramField:parameter.getParamFieldList()){
                    ParamFieldBO insertBO= convertToParamFieldBO(paramField);
                    if(paramFieldDBBOList.contains(insertBO)){
                        paramFieldDBBOList.remove(insertBO);
                        continue;
                    }
                    ParamFieldBO paramFieldBO=paramFieldBusService.persist(insertBO);
                    paramFieldDBBOList.remove(paramFieldBO);
                }
            }

            //删除去掉的属性
            for(ParamFieldBO paramFieldBO:paramFieldDBBOList){
                paramFieldBusService.deleteById(paramFieldBO.getId());
            }
        }
    }

    private void handleParamType(InterfaceDetailBO interfaceDetailBO,ApiDoc apiDoc){
        dealParamList(interfaceDetailBO, apiDoc.getReqParamList(), ParamTypeBO.RESOURCE_REQ);
        dealParamList(interfaceDetailBO, apiDoc.getRespParamList(), ParamTypeBO.RESOURCE_RESP);
    }

    private void dealParamList(InterfaceDetailBO interfaceDetailBO,List<Parameter> parameterList,String type){

        if(CollectionUtils.isEmpty(parameterList)){
            return;
        }
        for(Parameter parameter: parameterList){
            ParamTypeBO paramTypeBO=paramTypeBusService.persist(interfaceDetailBO.getId(), parameter.getName(),type);
            List<ParamFieldBO> paramFieldDBBOList=paramFieldBusService.queryByParamTypeId(paramTypeBO.getId());
            if(!CollectionUtils.isEmpty(parameter.getParamFieldList())){
                for(ParamField paramField:parameter.getParamFieldList()){
                    ParamFieldBO insertBO= convertToParamFieldBO(paramField);
                    if(paramFieldDBBOList.contains(insertBO)){
                        paramFieldDBBOList.remove(insertBO);
                        continue;
                    }
                    ParamFieldBO paramFieldBO=paramFieldBusService.persist(insertBO);
                    paramFieldDBBOList.remove(paramFieldBO);
                    handleRefGeneric(interfaceDetailBO.getSystemVersionId(),paramTypeBO,paramField.getRefGenericClassNameList());
                }
            }
            for(ParamFieldBO paramFieldBO:paramFieldDBBOList){
                paramFieldBusService.deleteById(paramFieldBO.getId());
            }
        }
    }


    private ParamFieldBO convertToParamFieldBO(ParamField paramField){
        ParamFieldBO paramFieldBO=new ParamFieldBO();
        paramFieldBO.setDefaultValue(paramField.getDefaultValue());
        paramFieldBO.setExample(paramField.getExample());
        paramFieldBO.setParamDescription(paramField.getDesrciption());
        paramFieldBO.setParamLength(paramField.getLength());
        paramFieldBO.setParamName(paramField.getName());
        paramFieldBO.setParamType(paramField.getType());
        paramFieldBO.setRequired(paramField.getRequired());
        return paramFieldBO;
    }

    private InterfaceDetailBO convertApiDoc2InterfaceDetail(SystemVersionBO systemVersionBO,InterfaceCatagoryBO interfaceCatagoryBO,ApiDoc apiDoc){
        InterfaceDetailBO interfaceDetailBO=new InterfaceDetailBO();
        interfaceDetailBO.setCatagoryId(interfaceCatagoryBO.getId());
        interfaceDetailBO.setAddress(apiDoc.getAddress());
        interfaceDetailBO.setDescription(apiDoc.getDescription());
        interfaceDetailBO.setName(String.format(("%s#%s"), apiDoc.getClassName(), apiDoc.getMethodName()));
        interfaceDetailBO.setStatus(apiDoc.getStatus());
        interfaceDetailBO.setSystemName(systemVersionBO.getSystemName());
        interfaceDetailBO.setSystemVersionId(systemVersionBO.getId());
        interfaceDetailBO.setCaller(toString(apiDoc.getCallers()));
        interfaceDetailBO.setMemo(apiDoc.getMemo());
        interfaceDetailBO.setType(apiDoc.getType());
        interfaceDetailBO.setRequestType(apiDoc.getRequestType());
        return interfaceDetailBO;
    }

    private void handleRefGeneric(Integer systemVersionId ,ParamTypeBO paramTypeBO,List<String> genericClassList){
        if(CollectionUtils.isEmpty(genericClassList)){
           return;
        }
        for(String classsName:genericClassList){
            ParamGenericTypeBO paramGenericTypeBO=paramGenericTypeBusService.queryBySystemVersionIdAndName(systemVersionId,classsName);
            paramTypeRefGenericBusService.persist(paramTypeBO.getId(),paramGenericTypeBO.getId());
        }
    }


    private <T> String toString(Collection<T> collection){
        String result="";
        for(T t:collection){
            result=result+","+t;
        }
        return result;
    }
}
