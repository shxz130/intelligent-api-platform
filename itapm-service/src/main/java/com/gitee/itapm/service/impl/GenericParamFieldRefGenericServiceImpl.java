package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.GenericParamFieldRefGenericDOMapper;
import com.gitee.itapm.mapper.ParamFieldRefGenericDOMapper;
import com.gitee.itapm.mapper.bean.GenericParamFieldRefGenericDO;
import com.gitee.itapm.mapper.bean.ParamFieldRefGenericDO;
import com.gitee.itapm.service.GenericParamTypeRefGenericService;
import com.gitee.itapm.service.ParamTypeRefGenericService;
import com.gitee.itapm.service.bean.GenericParamFieldRefGenericBO;
import com.gitee.itapm.service.bean.ParamFieldRefGenericBO;
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
public class GenericParamFieldRefGenericServiceImpl implements GenericParamTypeRefGenericService {


    @Autowired
    private GenericParamFieldRefGenericDOMapper genericParamFieldRefGenericDOMapper;

    @Override
    public GenericParamFieldRefGenericBO queryByParamAndGenericTypeId(Integer paramTypeId, Integer genericTypeId) {
        GenericParamFieldRefGenericDO paramTypeRefGenericDO=genericParamFieldRefGenericDOMapper.queryByParamAndGenericTypeId(paramTypeId,genericTypeId);
        if(paramTypeRefGenericDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(paramTypeRefGenericDO,GenericParamFieldRefGenericBO.class);
    }

    @Override
    public GenericParamFieldRefGenericBO persist(Integer paramFieldId, Integer genericTypeId) {
        GenericParamFieldRefGenericDO genericParamFieldRefGenericDO=new GenericParamFieldRefGenericDO();
        genericParamFieldRefGenericDO.setGenericTypeId(genericTypeId);
        genericParamFieldRefGenericDO.setGenericParamFieldId(paramFieldId);
        genericParamFieldRefGenericDOMapper.persist(genericParamFieldRefGenericDO);
        return BeanCopierUtils.copyOne2One(genericParamFieldRefGenericDO,GenericParamFieldRefGenericBO.class);
    }

    @Override
    public void deleteByParamTypeId(Integer id) {
        genericParamFieldRefGenericDOMapper.deleteById(id);
    }

    @Override
    public List<GenericParamFieldRefGenericBO> queryByFieldId(Integer fieldId) {
        List<GenericParamFieldRefGenericDO> paramTypeRefGenericDOList=genericParamFieldRefGenericDOMapper.queryByFiledId(fieldId);
        if(CollectionUtils.isEmpty(paramTypeRefGenericDOList)){
            return Collections.emptyList();
        }
        return BeanCopierUtils.copyList2List(paramTypeRefGenericDOList,GenericParamFieldRefGenericBO.class);
    }

    @Override
    public void deleteById(Integer id) {
        genericParamFieldRefGenericDOMapper.deleteById(id);
    }
}
