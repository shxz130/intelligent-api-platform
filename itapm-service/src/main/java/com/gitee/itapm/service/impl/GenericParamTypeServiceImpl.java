package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.GenericParamFieldDOMapper;
import com.gitee.itapm.mapper.ParamFieldDOMapper;
import com.gitee.itapm.mapper.ParamTypeDOMapper;
import com.gitee.itapm.mapper.bean.GenericParamFieldDO;
import com.gitee.itapm.mapper.bean.ParamFieldDO;
import com.gitee.itapm.mapper.bean.ParamTypeDO;
import com.gitee.itapm.service.GenericParamFieldService;
import com.gitee.itapm.service.ParamTypeService;
import com.gitee.itapm.service.bean.GenericParamFieldBO;
import com.gitee.itapm.service.bean.ParamFieldBO;
import com.gitee.itapm.service.bean.ParamTypeBO;
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
public class GenericParamTypeServiceImpl implements GenericParamFieldService{


    @Autowired
    private GenericParamFieldDOMapper genericParamFieldDOMapper;

    @Override
    public GenericParamFieldBO queryByParamTypeIdAndParamName(Integer paramTypeId, String name) {
        GenericParamFieldDO paramFieldDO=genericParamFieldDOMapper.queryByParamTypeIdAndParamName(paramTypeId,name);
        if(paramFieldDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(paramFieldDO,GenericParamFieldBO.class);
    }

    @Override
    public List<GenericParamFieldBO> queryByParamTypeId(Integer paramTypeId) {
        List<GenericParamFieldDO> paramFieldDOList=genericParamFieldDOMapper.queryByParamTypeId(paramTypeId);
        if(CollectionUtils.isEmpty(paramFieldDOList)){
            return Collections.emptyList();
        }
        return BeanCopierUtils.copyList2List(paramFieldDOList,GenericParamFieldBO.class);
    }

    @Override
    public Integer deleteById(Integer id) {
        return genericParamFieldDOMapper.deleteById(id);
    }

    @Override
    public void persist(GenericParamFieldBO paramFieldBO) {
        GenericParamFieldDO paramFieldDO=BeanCopierUtils.copyOne2One(paramFieldBO, GenericParamFieldDO.class);
        genericParamFieldDOMapper.persist(paramFieldDO);
        paramFieldBO.setId(paramFieldDO.getId());
    }

    @Override
    public Integer updateById(GenericParamFieldBO paramFieldBO) {
        GenericParamFieldDO paramFieldDO=BeanCopierUtils.copyOne2One(paramFieldBO,GenericParamFieldDO.class);
        return genericParamFieldDOMapper.updateById(paramFieldDO);
    }
}
