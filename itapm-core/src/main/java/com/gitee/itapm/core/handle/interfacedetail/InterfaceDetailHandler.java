package com.gitee.itapm.core.handle.interfacedetail;

import com.gitee.itapm.core.handle.bean.InterfaceDetailReq;
import com.gitee.itapm.core.handle.bean.InterfaceDetailResp;
import com.gitee.itapm.core.handle.parent.AbstractHandler;
import com.gitee.itapm.service.bean.*;
import com.gitee.itapm.service.bus.*;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.IdGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    private Integer id;

    @Override
    protected void doHandle(InterfaceDetailReq request, InterfaceDetailResp response, Map map) {
        InterfaceDetailBO interfaceDetailBO= interfaceDetailBusService.queryById(request.getInterfaceDetailId());
        ParamTypeBO reqParamTypeBO=paramTypeBusService.queryByInterfaceDetailIdAndResource(interfaceDetailBO.getId(), "REQ");
        ParamTypeBO respPramTypeBO=paramTypeBusService.queryByInterfaceDetailIdAndResource(interfaceDetailBO.getId(), "RESP");
        id=new Integer(1);//用来重新生成ID给前段展示
        reqParamTypeBO.setParamFieldList(getParamFieldListByParamTypeId(reqParamTypeBO.getId()));
        respPramTypeBO.setParamFieldList(getParamFieldListByParamTypeId(respPramTypeBO.getId()));
        interfaceDetailBO.setReqParamTypeBO(reqParamTypeBO);
        interfaceDetailBO.setRespParamTypeBO(respPramTypeBO);
        response.setInterfaceDetail(interfaceDetailBO);
    }


    public List<ParamFieldBO> getParamFieldListByParamTypeId(Integer paramTypeId){
        List<ParamFieldBO> resultList=new ArrayList<>();

        List<ParamFieldBO> paramFieldList=paramFieldBusService.queryByParamTypeId(paramTypeId);
        for(ParamFieldBO paramFieldBO : paramFieldList){
            resultList.add(paramFieldBO);
            List<ParamFieldRefGenericBO> paramTypeRefGenericList=paramTypeRefGenericBusService.queryByFieldId(paramFieldBO.getId());
            paramFieldBO.setId(getNextId());//这里特殊处理，用于前端排序
            for(ParamFieldRefGenericBO paramFieldRefGenericBO: paramTypeRefGenericList){
                ParamGenericTypeBO paramGenericTypeBO= paramGenericTypeBusService.queryById(paramFieldRefGenericBO.getGenericTypeId());
                List<GenericParamFieldBO> genericParamFieldBOList=genericParamFieldBusService.queryByParamTypeId(paramGenericTypeBO.getId());
                resultList.addAll(convert(paramFieldBO, genericParamFieldBOList));
            }
        }
        return resultList;
    }

    private List<ParamFieldBO> convert(ParamFieldBO parent,List<GenericParamFieldBO> genericParamFieldBOList) {
        if(CollectionUtils.isEmpty(genericParamFieldBOList)){
            return Collections.emptyList();
        }
        List<ParamFieldBO> paramFieldList=new ArrayList<>();
        for(GenericParamFieldBO genericParamFieldBO: genericParamFieldBOList){
            ParamFieldBO paramField= BeanCopierUtils.copyOne2One(genericParamFieldBO,ParamFieldBO.class);
            paramField.setId(getNextId());
            paramField.setParentId(parent.getId());
            paramFieldList.add(paramField);
        }
        parent.setHasGenericParam(true);
        return paramFieldList;
    }

    private Integer getNextId(){
        return id++;
    }
}
