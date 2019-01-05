package com.gitee.itapm.core.handle.interfacedetail;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gitee.itapm.core.handle.bean.InterfaceDetailReq;
import com.gitee.itapm.core.handle.bean.InterfaceDetailResp;
import com.gitee.itapm.core.handle.parent.AbstractHandler;
import com.gitee.itapm.service.bean.*;
import com.gitee.itapm.service.bus.*;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import com.gitee.itapm.utils.reflect.DefaultValueGenericUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by jetty on 2018/12/15.
 */

@Component
public class InterfaceDetailHandler extends AbstractHandler<InterfaceDetailReq,InterfaceDetailResp> {


    @Autowired
    private InterfaceDetailBusService interfaceDetailBusService;
    @Autowired
    private ParamTypeBusService paramTypeBusService;
    @Autowired
    private ParamFieldBusService paramFieldBusService;
    @Autowired
    private ParamTypeRefGenericBusService paramTypeRefGenericBusService;
    @Autowired
    private ParamGenericTypeBusService paramGenericTypeBusService;
    @Autowired
    private GenericParamFieldBusService genericParamFieldBusService;
    @Autowired
    private GenericParamTypeRefGenericBusService genericParamTypeRefGenericBusService;

    private ThreadLocal<Integer> threadLocalId=new ThreadLocal<>();

    @Override
    protected void doHandle(InterfaceDetailReq request, InterfaceDetailResp response, Map map) {
        try{
            InterfaceDetailBO interfaceDetailBO= interfaceDetailBusService.queryById(request.getInterfaceDetailId());
            ParamTypeBO reqParamTypeBO=paramTypeBusService.queryByInterfaceDetailIdAndResource(interfaceDetailBO.getId(), "REQ");
            ParamTypeBO respPramTypeBO=paramTypeBusService.queryByInterfaceDetailIdAndResource(interfaceDetailBO.getId(), "RESP");
            if(reqParamTypeBO!=null){
                ParamDetail paramDetail=getParamFieldListByParamTypeId(reqParamTypeBO.getId());
                reqParamTypeBO.setParamFieldList(paramDetail.getParamFieldList());
                reqParamTypeBO.setJsonFormat(JSON.toJSONString(paramDetail.getJsonMapData()));
            }
            if(respPramTypeBO!=null){
                ParamDetail paramDetail=getParamFieldListByParamTypeId(respPramTypeBO.getId());
                respPramTypeBO.setParamFieldList(paramDetail.getParamFieldList());
                respPramTypeBO.setJsonFormat(JSON.toJSONString(paramDetail.getJsonMapData()));
            }
            interfaceDetailBO.setReqParamTypeBO(reqParamTypeBO);
            interfaceDetailBO.setRespParamTypeBO(respPramTypeBO);
            response.setInterfaceDetail(interfaceDetailBO);
        }finally {
            threadLocalId.remove();
        }

    }


    public ParamDetail getParamFieldListByParamTypeId(Integer paramTypeId){
        List<ParamFieldBO> resultList=new ArrayList<>();//获取参数类型下面的所有子参数和类型

        Map<String,Object> paramFieldsMap1=new HashMap<>();//保存所有参数及默认值，用于初始化json格式数据

        List<ParamFieldBO> paramFieldList=paramFieldBusService.queryByParamTypeId(paramTypeId);
        for(ParamFieldBO paramFieldBO : paramFieldList) {

            if(getCollectionByType(paramFieldBO.getParamType())!=null){
               Collection c= getCollectionByType(paramFieldBO.getParamType());
                c.add(DefaultValueGenericUtils.getDefaultValueByJavaType(paramFieldBO.getParamType()));
                paramFieldsMap1.put(paramFieldBO.getParamName(),c);

            }else{
                paramFieldsMap1.put(paramFieldBO.getParamName(),DefaultValueGenericUtils.getDefaultValueByJavaType(paramFieldBO.getParamType()));
            }

            resultList.add(paramFieldBO);
            List<ParamFieldRefGenericBO> paramTypeRefGenericList = paramTypeRefGenericBusService.queryByFieldId(paramFieldBO.getId());
            paramFieldBO.setId(getNextId());//这里特殊处理，用于前端排序

            if(CollectionUtils.isEmpty(paramTypeRefGenericList)){
                continue;
            }

            Map<String,Object> paramFieldsMap2=new HashMap<>();//保存json格式数据的map
            for (ParamFieldRefGenericBO paramFieldRefGenericBO : paramTypeRefGenericList) {
                //这里对应每一个参数进行处理
                ParamGenericTypeBO paramGenericTypeBO = paramGenericTypeBusService.queryById(paramFieldRefGenericBO.getGenericTypeId());
                List<GenericParamFieldBO> genericParamFieldBOList = genericParamFieldBusService.queryByParamTypeId(paramGenericTypeBO.getId());
                if (CollectionUtils.isEmpty(genericParamFieldBOList)) {
                    continue;
                }
                //list为泛型类型处理 泛型对应的所有参数
                for (GenericParamFieldBO genericParamFieldBO : genericParamFieldBOList) {
                    //每一个参数对应的泛化类型，或者自定义类型的参数进行处理
                    if(getCollectionByType(genericParamFieldBO.getParamType())!=null){
                        Collection c=getCollectionByType(genericParamFieldBO.getParamType());
                        c.add(DefaultValueGenericUtils.getDefaultValueByJavaType(genericParamFieldBO.getParamType()));
                        paramFieldsMap2.put(genericParamFieldBO.getParamName(), c);
                    }else{
                        paramFieldsMap2.put(genericParamFieldBO.getParamName(), DefaultValueGenericUtils.getDefaultValueByJavaType(genericParamFieldBO.getParamType()));
                    }


                    //这里将GenericParamFieldBO convert为 ParamFieldBO coonvert方法参数为list，所以这里定义了一个。
                    List<GenericParamFieldBO> genericParamFieldTempList = new ArrayList<>();
                    genericParamFieldTempList.add(genericParamFieldBO);
                    ParamDetail paramDetailList= convert(paramFieldBO, genericParamFieldTempList);
                    List<ParamFieldBO> genericFiledList =paramDetailList.getParamFieldList();
                    if (CollectionUtils.isEmpty(genericFiledList)) {
                        continue;
                    }
                    resultList.addAll(genericFiledList);
                    //这里取第一个是为了迁就上面的convert，本身只有一个参数，就是当前循环的参数
                    ParamFieldBO parentParamField = genericFiledList.get(0);

                    List<GenericParamFieldRefGenericBO> genericParamFieldRefGenericList = genericParamTypeRefGenericBusService.queryByFieldId(genericParamFieldBO.getId());

                    if (CollectionUtils.isEmpty(genericParamFieldRefGenericList)) {
                        continue;
                    }
                    Map<String,Object> paramFieldsMap3=new HashMap<>();
                    for (GenericParamFieldRefGenericBO genericParamFieldRefGenericBO : genericParamFieldRefGenericList) {
                        List<GenericParamFieldBO> genericRefGenericParamFieldList = genericParamFieldBusService.queryByParamTypeId(genericParamFieldRefGenericBO.getGenericTypeId());
                        ParamDetail paramDetail=convert(parentParamField, genericRefGenericParamFieldList);
                        resultList.addAll(paramDetail.getParamFieldList());
                        paramFieldsMap3=paramDetail.getJsonMapData();
                    }
                    if(!CollectionUtils.isEmpty(paramFieldsMap3)&&paramFieldsMap3.size()>=1){
                        if(getCollectionByType(genericParamFieldBO.getParamType())!=null){
                            Collection c=getCollectionByType(genericParamFieldBO.getParamType());
                            c.add(paramFieldsMap3);
                            paramFieldsMap2.put(genericParamFieldBO.getParamName(), c);
                        }else{
                            paramFieldsMap2.put(genericParamFieldBO.getParamName(), paramFieldsMap3);
                        }
                    }
                }
            }
            if(!CollectionUtils.isEmpty(paramFieldsMap2)&&paramFieldsMap2.size()>=1){
                if(getCollectionByType(paramFieldBO.getParamType())!=null){
                    Collection c=getCollectionByType(paramFieldBO.getParamType());
                    c.add(paramFieldsMap2);
                    paramFieldsMap1.put(paramFieldBO.getParamName(), c);
                }else{
                    paramFieldsMap1.put(paramFieldBO.getParamName(), paramFieldsMap2);
                }
            }else{
                if(getCollectionByType(paramFieldBO.getParamType())!=null){
                    Collection c=getCollectionByType(paramFieldBO.getParamType());
                    c.add(DefaultValueGenericUtils.getDefaultValueByJavaType(paramFieldBO.getParamType()));
                    paramFieldsMap1.put(paramFieldBO.getParamName(), c);
                }else{
                    paramFieldsMap1.put(paramFieldBO.getParamName(),DefaultValueGenericUtils.getDefaultValueByJavaType(paramFieldBO.getParamType()));
                }
            }
        }
        return new ParamDetail(resultList,paramFieldsMap1);
    }

    private ParamDetail convert(ParamFieldBO parent,List<GenericParamFieldBO> genericParamFieldBOList) {

        Map<String,Object> map=null;//用于对象转json

        if(CollectionUtils.isEmpty(genericParamFieldBOList)){
            return new ParamDetail(Collections.emptyList(),map);
        }
        List<ParamFieldBO> paramFieldList=new ArrayList<>();
        map=new HashMap<>();
        for(GenericParamFieldBO genericParamFieldBO: genericParamFieldBOList){
            ParamFieldBO paramField= BeanCopierUtils.copyOne2One(genericParamFieldBO,ParamFieldBO.class);
            paramField.setId(getNextId());
            paramField.setParentId(parent.getId());
            paramFieldList.add(paramField);
            map.put(paramField.getParamName(), DefaultValueGenericUtils.getDefaultValueByJavaType(paramField.getParamType()));
        }
        parent.setHasGenericParam(true);
        return new ParamDetail(paramFieldList,map);
    }


    private Integer getNextId(){
        if(threadLocalId.get()==null){
            threadLocalId.set(new Integer(1));
        }else{
            Integer current=threadLocalId.get();
            threadLocalId.set(new Integer(++current));
        }
       return threadLocalId.get();
    }

    @Getter
    @AllArgsConstructor
    class ParamDetail{
        List<ParamFieldBO> paramFieldList;
        Map jsonMapData;
    }


    public static  Collection getCollectionByType(String type){
        if(type.startsWith("List")){
            return new ArrayList<>();
        }
        if(type.startsWith("Set")){
            return new HashSet<>();
        }
        return null;
    }

}

