package com.gitee.itapm.service.impl;

import com.gitee.itapm.service.ParamTypeRefGenericService;
import com.gitee.itapm.service.bean.ParamTypeRefGenericBO;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class ParamTypeRefGenericServiceImpl implements ParamTypeRefGenericService {


    @Override
    public ParamTypeRefGenericBO queryByParamAndGenericTypeId(Integer paramTypeId, Integer genericTypeId) {
        return null;
    }

    @Override
    public ParamTypeRefGenericBO persist(Integer paramTypeId, Integer genericTypeId) {
        return null;
    }

    @Override
    public void deleteByParamTypeId(Integer id) {

    }
}
