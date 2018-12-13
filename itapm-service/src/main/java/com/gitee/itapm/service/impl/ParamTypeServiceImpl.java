package com.gitee.itapm.service.impl;

import com.gitee.itapm.service.ParamTypeService;
import com.gitee.itapm.service.bean.ParamTypeBO;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/13.
 */

@Component
public class ParamTypeServiceImpl implements ParamTypeService{


    @Override
    public ParamTypeBO queryByInterfaceDetailIdNameAndResource(Integer interfaceDetailId, String name, String resource) {
        return null;
    }

    @Override
    public ParamTypeBO persist(Integer interfaceDetailId, String name, String resource) {
        return null;
    }
}
