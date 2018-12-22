package com.gitee.itapm.core.task.api;


import com.gitee.itapm.paser.bean.*;

import com.gitee.itapm.service.bean.*;
import com.gitee.itapm.service.bus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


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
    @Autowired
    private GenericParamFieldBusService genericParamFieldBusService;

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

            catagoryList.remove(interfaceCatagoryBO);
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


            ParamGenericTypeBO paramGenericType=paramGenericTypeBusService.persist(systemVersionBO.getId(), parameter.getName());
            //保存
            //所有数据落库

            List<GenericParamFieldBO> currentDBList=genericParamFieldBusService.queryByParamTypeId(paramGenericType.getId());

            if(!CollectionUtils.isEmpty(parameter.getParamFieldList())){
                for(ParamField paramField:parameter.getParamFieldList()){
                    GenericParamFieldBO insertBO= convertToGenericParamFieldBO(paramGenericType.getId(), paramField);
                    genericParamFieldBusService.persist(insertBO);
                    currentDBList.remove(insertBO);
                }
            }

            for(GenericParamFieldBO genericParamFieldBO: currentDBList){
                genericParamFieldBusService.deleteById(genericParamFieldBO.getId());
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
            //如果没有设置参数，则不处理
            if(CollectionUtils.isEmpty(parameter.getParamFieldList())){
                continue;
            }

            ParamTypeBO paramTypeBO=paramTypeBusService.persist(interfaceDetailBO.getId(), parameter.getName(),type);
            List<ParamFieldBO> paramFieldDBBOList=paramFieldBusService.queryByParamTypeId(paramTypeBO.getId());
            if(!CollectionUtils.isEmpty(parameter.getParamFieldList())){
                for(ParamField paramField:parameter.getParamFieldList()){
                    ParamFieldBO insertBO= convertToParamFieldBO(paramTypeBO.getId(),paramField);
                    if(paramFieldDBBOList.contains(insertBO)){
                        paramFieldDBBOList.remove(insertBO);
                        continue;
                    }
                    ParamFieldBO paramFieldBO=paramFieldBusService.persist(insertBO);
                    paramFieldDBBOList.remove(paramFieldBO);
                    handleRefGeneric(interfaceDetailBO.getSystemVersionId(),paramFieldBO,paramField.getRefGenericClassNameList());
                }
            }
            for(ParamFieldBO paramFieldBO:paramFieldDBBOList){
                paramFieldBusService.deleteById(paramFieldBO.getId());
            }
            return;
        }
    }


    private ParamFieldBO convertToParamFieldBO(Integer parentId,ParamField paramField){
        ParamFieldBO paramFieldBO=new ParamFieldBO();
        paramFieldBO.setParamTypeId(parentId);
        paramFieldBO.setDefaultValue(paramField.getDefaultValue());
        paramFieldBO.setExample(paramField.getExample());
        paramFieldBO.setParamDescription(paramField.getDesrciption());
        paramFieldBO.setParamLength(paramField.getLength());
        paramFieldBO.setParamName(paramField.getName());
        paramFieldBO.setParamType(paramField.getType());
        paramFieldBO.setRequired(paramField.getRequired());
        return paramFieldBO;
    }

    private GenericParamFieldBO convertToGenericParamFieldBO(Integer parentId,ParamField paramField){
        GenericParamFieldBO paramFieldBO=new GenericParamFieldBO();
        paramFieldBO.setGenericParamTypeId(parentId);
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

    private void handleRefGeneric(Integer systemVersionId ,ParamFieldBO paramFieldBO,List<String> genericClassList){
        if(CollectionUtils.isEmpty(genericClassList)){
           return;
        }
        for(String classsName:genericClassList){
            ParamGenericTypeBO paramGenericTypeBO=paramGenericTypeBusService.queryBySystemVersionIdAndName(systemVersionId,classsName);
            if(paramGenericTypeBO !=null){
                paramTypeRefGenericBusService.persist(paramFieldBO.getId(),paramGenericTypeBO.getId());
            }
        }
    }


    private <T> String toString(List<T> collection){
        String result="";

        for(int i=0;i<collection.size();i++){
            if(collection.size()==1){
                result= collection.get(i).toString();
            }else {
                result=result+","+collection.get(i);
            }
        }
        return result;
    }
}
