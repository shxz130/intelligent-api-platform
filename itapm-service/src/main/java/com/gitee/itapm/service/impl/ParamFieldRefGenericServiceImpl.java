package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.ParamFieldRefGenericDOMapper;
import com.gitee.itapm.mapper.bean.ParamFieldRefGenericDO;
import com.gitee.itapm.service.ParamTypeRefGenericService;
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
public class ParamFieldRefGenericServiceImpl implements ParamTypeRefGenericService {


    @Autowired
    private ParamFieldRefGenericDOMapper paramTypeRefGenericDOMapper;

    @Override
    public ParamFieldRefGenericBO queryByParamAndGenericTypeId(Integer paramTypeId, Integer genericTypeId) {
        ParamFieldRefGenericDO paramTypeRefGenericDO=paramTypeRefGenericDOMapper.queryByParamAndGenericTypeId(paramTypeId,genericTypeId);
        if(paramTypeRefGenericDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(paramTypeRefGenericDO,ParamFieldRefGenericBO.class);
    }

    @Override
    public ParamFieldRefGenericBO persist(Integer paramFieldId, Integer genericTypeId) {
        ParamFieldRefGenericDO paramTypeRefGenericDO=new ParamFieldRefGenericDO();
        paramTypeRefGenericDO.setGenericTypeId(genericTypeId);
        paramTypeRefGenericDO.setParamFieldId(paramFieldId);
        paramTypeRefGenericDOMapper.persist(paramTypeRefGenericDO);
        return BeanCopierUtils.copyOne2One(paramTypeRefGenericDO,ParamFieldRefGenericBO.class);
    }

    @Override
    public void deleteByParamTypeId(Integer id) {
        paramTypeRefGenericDOMapper.deleteById(id);
    }

    @Override
    public List<ParamFieldRefGenericBO> queryByFieldId(Integer fieldId) {
        List<ParamFieldRefGenericDO> paramTypeRefGenericDOList=paramTypeRefGenericDOMapper.queryByFiledId(fieldId);
        if(CollectionUtils.isEmpty(paramTypeRefGenericDOList)){
            return Collections.emptyList();
        }
        return BeanCopierUtils.copyList2List(paramTypeRefGenericDOList,ParamFieldRefGenericBO.class);
    }

    @Override
    public void deleteById(Integer id) {
        paramTypeRefGenericDOMapper.deleteById(id);
    }
}
