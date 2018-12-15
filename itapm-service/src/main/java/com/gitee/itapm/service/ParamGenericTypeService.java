package com.gitee.itapm.service;

import com.gitee.itapm.service.bean.GenericParamFieldBO;
import com.gitee.itapm.service.bean.ParamGenericTypeBO;

/**
 * Created by jetty on 2018/12/13.
 */
public interface ParamGenericTypeService {

    public ParamGenericTypeBO queryBySystemVersionIdAndName(Integer typeId,String name);

    public ParamGenericTypeBO persist(Integer systemVersionId,String name);

    ParamGenericTypeBO queryById(Integer genericTypeId);
}
