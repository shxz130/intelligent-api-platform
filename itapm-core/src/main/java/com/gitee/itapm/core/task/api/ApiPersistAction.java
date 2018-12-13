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
        SystemInfoBO systemInfoBO=systemInfoBusService.persist(document.getSystemEnName(), document.getSystemChName());
        SystemVersionBO systemVersionBO=systemVersionBusService.persist(systemInfoBO, document.getVersion());

        for(Catagory catagory:document.getCatagoryList()){
           InterfaceCatagoryBO interfaceCatagoryBO= interfaceCatagoryBusService.persist(systemVersionBO.getId(), catagory.getCatagoryName());
            handleApiDoc(systemVersionBO, interfaceCatagoryBO, catagory.getApiDocList());
        }

    }

    private void handleApiDoc(SystemVersionBO systemVersionBO,InterfaceCatagoryBO interfaceCatagoryBO,List<ApiDoc> apiDocList){
        if(CollectionUtils.isEmpty(apiDocList)){
            return;
        }
        List<InterfaceDetailBO> currentSystemInterfaceDetailList=interfaceDetailBusService.queryByCatagoryId(interfaceCatagoryBO.getId());
        for(ApiDoc apiDoc:apiDocList){
            //处理泛型
            handleGeneric(systemVersionBO,apiDoc,apiDoc.getGenericParameterList());
            //接口明细落库
            InterfaceDetailBO interfaceDetailBO=interfaceDetailBusService.persist(convertApiDoc2InterfaceDetail(systemVersionBO, interfaceCatagoryBO, apiDoc));
            handleParamType(interfaceDetailBO, apiDoc);
            currentSystemInterfaceDetailList.remove(interfaceDetailBO);
        }
        for(InterfaceDetailBO interfaceDetailBO:currentSystemInterfaceDetailList){
            interfaceDetailBusService.deleteById(interfaceDetailBO.getId());
        }
    }

    private void handleGeneric(SystemVersionBO systemVersionBO,ApiDoc apiDoc,List<Parameter> parameterList){
        if(!CollectionUtils.isEmpty(parameterList)){
            for(Parameter parameter:parameterList){
                ParamGenericTypeBO paramGenericType=paramGenericTypeBusService.persist(systemVersionBO.getId(), parameter.getName());
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
                for(ParamFieldBO paramFieldBO:paramFieldDBBOList){
                    paramFieldBusService.deleteById(paramFieldBO.getId());
                }
            }
        }
    }


    private void handleParamType(InterfaceDetailBO interfaceDetailBO,ApiDoc apiDoc){
        List<Parameter> reqParamList= apiDoc.getReqParamList();
        for(Parameter parameter: reqParamList){
            ParamTypeBO paramTypeBO=paramTypeBusService.persist(interfaceDetailBO.getId(), parameter.getName(),ParamTypeBO.RESOURCE_REQ);
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


    private void handleParamField(Parameter parameter){
        if(!CollectionUtils.isEmpty(parameter.getParamFieldList())){
            for(ParamField paramField: parameter.getParamFieldList()){
                paramFieldBusService.persist(convertToParamFieldBO(paramField));
                handleGenericType(paramField, paramField.getRefGenericClassNameList());
            }
        }

    }

    private void handleGenericType(ParamField paramField,List<String> string){

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
        if(!CollectionUtils.isEmpty(genericClassList)){
            for(String classsName:genericClassList){
                ParamGenericTypeBO paramGenericTypeBO=paramGenericTypeBusService.queryBySystemVersionIdAndName(systemVersionId,classsName);
                paramTypeRefGenericBusService.persist(paramTypeBO.getId(),paramGenericTypeBO.getId());
            }
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
