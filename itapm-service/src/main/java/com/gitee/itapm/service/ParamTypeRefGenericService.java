package com.gitee.itapm.service;

import com.gitee.itapm.service.bean.ParamTypeRefGenericBO;

/**
 * Created by jetty on 2018/12/13.
 */

public interface ParamTypeRefGenericService {


    public ParamTypeRefGenericBO queryByParamAndGenericTypeId(Integer paramTypeId,Integer genericTypeId);

    public ParamTypeRefGenericBO persist(Integer paramTypeId,Integer genericTypeId);

    public void deleteByParamTypeId(Integer id);
}
