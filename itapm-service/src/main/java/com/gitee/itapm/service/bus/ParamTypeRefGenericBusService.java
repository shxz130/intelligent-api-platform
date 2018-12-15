package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.ParamTypeRefGenericService;
import com.gitee.itapm.service.bean.ParamFieldRefGenericBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class ParamTypeRefGenericBusService {


    @Autowired
    private ParamTypeRefGenericService paramTypeRefGenericService;


    public synchronized ParamFieldRefGenericBO persist(Integer paramFiledId,Integer genericTypeId){
        ParamFieldRefGenericBO paramGenericTypeBO = paramTypeRefGenericService.queryByParamAndGenericTypeId(paramFiledId, genericTypeId);
        if(paramGenericTypeBO==null){
            paramGenericTypeBO =paramTypeRefGenericService.persist(paramFiledId, genericTypeId);
        }
        return paramGenericTypeBO;
    }

    public List<ParamFieldRefGenericBO> queryByFieldId(Integer fieldId) {
        List<ParamFieldRefGenericBO> paramTypeRefGenericList= paramTypeRefGenericService.queryByFieldId(fieldId);
        if(CollectionUtils.isEmpty(paramTypeRefGenericList)){
            return Collections.emptyList();
        }
        return BeanCopierUtils.copyList2List(paramTypeRefGenericList,ParamFieldRefGenericBO.class);
    }
}
