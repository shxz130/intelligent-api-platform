package com.gitee.itapm.service;

import com.gitee.itapm.service.bean.GenericParamFieldRefGenericBO;
import com.gitee.itapm.service.bean.ParamFieldRefGenericBO;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */

public interface GenericParamTypeRefGenericService {


    public GenericParamFieldRefGenericBO queryByParamAndGenericTypeId(Integer genericParamTypeId, Integer genericTypeId);

    public GenericParamFieldRefGenericBO persist(Integer genericParamTypeId, Integer genericTypeId);

    public void deleteByParamTypeId(Integer id);

    List<GenericParamFieldRefGenericBO> queryByFieldId(Integer fieldId);

    void deleteById(Integer id);
}
