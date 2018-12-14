package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.ParamFieldDOMapper;
import com.gitee.itapm.mapper.bean.ParamFieldDO;
import com.gitee.itapm.service.ParamFieldService;
import com.gitee.itapm.service.bean.ParamFieldBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class ParamFieldServiceImpl implements ParamFieldService{


    @Autowired
    private ParamFieldDOMapper paramFieldDOMapper;

    @Override
    public ParamFieldBO queryByParamTypeIdAndParamName(Integer paramTypeId, String name) {
        ParamFieldDO paramFieldDO=paramFieldDOMapper.queryByParamTypeIdAndParamName(paramTypeId,name);
        if(paramFieldDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(paramFieldDO,ParamFieldBO.class);
    }

    @Override
    public List<ParamFieldBO> queryByParamTypeId(Integer paramTypeId) {
        List<ParamFieldDO> paramFieldDOList=paramFieldDOMapper.queryByParamTypeId(paramTypeId);
        if(CollectionUtils.isEmpty(paramFieldDOList)){
            return Collections.emptyList();
        }
        return BeanCopierUtils.copyList2List(paramFieldDOList,ParamFieldBO.class);
    }

    @Override
    public Integer deleteById(Integer id) {
        return paramFieldDOMapper.deleteById(id);
    }

    @Override
    public ParamFieldBO persist(ParamFieldBO paramFieldBO) {
        ParamFieldDO paramFieldDO=BeanCopierUtils.copyOne2One(paramFieldBO,ParamFieldDO.class);
        paramFieldDOMapper.persist(paramFieldDO);
        return BeanCopierUtils.copyOne2One(paramFieldDO,ParamFieldBO.class);
    }

    @Override
    public Integer updateById(ParamFieldBO paramFieldBO) {
        ParamFieldDO paramFieldDO=BeanCopierUtils.copyOne2One(paramFieldBO,ParamFieldDO.class);
        return paramFieldDOMapper.updateById(paramFieldDO);
    }
}
