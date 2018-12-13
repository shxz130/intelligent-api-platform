package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.ParamGenericTypeService;
import com.gitee.itapm.service.ParamTypeRefGenericService;
import com.gitee.itapm.service.bean.ParamGenericTypeBO;
import com.gitee.itapm.service.bean.ParamTypeRefGenericBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class ParamTypeRefGenericBusService {


    @Autowired
    private ParamTypeRefGenericService paramTypeRefGenericService;


    public synchronized ParamTypeRefGenericBO persist(Integer paramTypeId,Integer genericTypeId){
        ParamTypeRefGenericBO paramGenericTypeBO = paramTypeRefGenericService.queryByParamAndGenericTypeId(paramTypeId, genericTypeId);
        if(paramGenericTypeBO==null){
            paramGenericTypeBO =paramTypeRefGenericService.persist(paramTypeId, genericTypeId);
        }
        return paramGenericTypeBO;
    }

    public void deleteByParamTypeId(Integer paramTypeId){
        paramTypeRefGenericService.deleteByParamTypeId(paramTypeId);
    }
}
