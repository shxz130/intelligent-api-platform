package com.gitee.itapm.service;

import com.gitee.itapm.service.bean.ParamFieldBO;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
public interface ParamFieldService {

    public ParamFieldBO queryByParamTypeIdAndParamName(Integer paramTypeId,String name);

    public List<ParamFieldBO> queryByParamTypeId(Integer paramTypeId);

    public Integer deleteById(Integer id);

    public ParamFieldBO persist(ParamFieldBO paramFieldBO);

    public Integer updateById(ParamFieldBO paramFieldBO);
}
