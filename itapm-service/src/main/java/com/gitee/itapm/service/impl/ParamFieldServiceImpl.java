package com.gitee.itapm.service.impl;

import com.gitee.itapm.service.ParamFieldService;
import com.gitee.itapm.service.bean.ParamFieldBO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class ParamFieldServiceImpl implements ParamFieldService{

    @Override
    public ParamFieldBO queryByParamTypeIdAndParamName(Integer paramTypeId, String name) {
        return null;
    }

    @Override
    public List<ParamFieldBO> queryByParamTypeId(Integer paramTypeId) {
        return null;
    }

    @Override
    public Integer deleteById(Integer id) {
        return null;
    }

    @Override
    public ParamFieldBO persist(ParamFieldBO paramFieldBO) {
        return null;
    }

    @Override
    public Integer updateById(ParamFieldBO paramFieldBO) {
        return null;
    }
}
