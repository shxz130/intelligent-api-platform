package com.gitee.itapm.service;

import com.gitee.itapm.service.bean.ParamTypeBO;

/**
 * Created by jetty on 2018/12/13.
 */
public interface ParamTypeService {

    public ParamTypeBO queryByInterfaceDetailIdResource(Integer interfaceDetailId, String resource);

    public ParamTypeBO persist(Integer interfaceDetailId,String name,String resource);

    void deleteById(Integer id);
}
