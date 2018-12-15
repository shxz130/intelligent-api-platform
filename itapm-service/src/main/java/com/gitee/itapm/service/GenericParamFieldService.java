package com.gitee.itapm.service;

import com.gitee.itapm.service.bean.GenericParamFieldBO;
import com.gitee.itapm.service.bean.ParamFieldBO;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
public interface GenericParamFieldService {

    public GenericParamFieldBO queryByParamTypeIdAndParamName(Integer paramTypeId, String name);

    public List<GenericParamFieldBO> queryByParamTypeId(Integer paramTypeId);

    public Integer deleteById(Integer id);

    public void persist(GenericParamFieldBO genericParamFieldBO);

    public Integer updateById(GenericParamFieldBO genericParamFieldBO);
}
