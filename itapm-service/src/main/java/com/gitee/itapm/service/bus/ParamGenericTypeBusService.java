package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.ParamGenericTypeService;
import com.gitee.itapm.service.bean.GenericParamFieldBO;
import com.gitee.itapm.service.bean.ParamGenericTypeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class ParamGenericTypeBusService {

    @Autowired
    private ParamGenericTypeService paramGenericTypeService;

    public synchronized ParamGenericTypeBO persist(Integer systemVersionId,String name){
        ParamGenericTypeBO paramGenericTypeBO=paramGenericTypeService.queryBySystemVersionIdAndName(systemVersionId, name);
        if(paramGenericTypeBO==null){
            paramGenericTypeBO= paramGenericTypeService.persist(systemVersionId,name);
        }
        return paramGenericTypeBO;
    }

    public ParamGenericTypeBO queryBySystemVersionIdAndName(Integer systemVersionId,String name){
        return paramGenericTypeService.queryBySystemVersionIdAndName(systemVersionId,name);
    }

    public ParamGenericTypeBO queryById(Integer genericTypeId) {
        return paramGenericTypeService.queryById(genericTypeId);
    }
}
