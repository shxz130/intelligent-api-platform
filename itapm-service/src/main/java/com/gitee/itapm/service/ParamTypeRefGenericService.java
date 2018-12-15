package com.gitee.itapm.service;

import com.gitee.itapm.service.bean.ParamFieldRefGenericBO;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */

public interface ParamTypeRefGenericService {


    public ParamFieldRefGenericBO queryByParamAndGenericTypeId(Integer paramTypeId,Integer genericTypeId);

    public ParamFieldRefGenericBO persist(Integer paramTypeId,Integer genericTypeId);

    public void deleteByParamTypeId(Integer id);

    List<ParamFieldRefGenericBO> queryByFieldId(Integer fieldId);
}
