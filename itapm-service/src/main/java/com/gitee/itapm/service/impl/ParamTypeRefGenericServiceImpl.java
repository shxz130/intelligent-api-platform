package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.ParamTypeRefGenericDOMapper;
import com.gitee.itapm.mapper.bean.ParamTypeRefGenericDO;
import com.gitee.itapm.service.ParamTypeRefGenericService;
import com.gitee.itapm.service.bean.ParamTypeRefGenericBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class ParamTypeRefGenericServiceImpl implements ParamTypeRefGenericService {


    @Autowired
    private ParamTypeRefGenericDOMapper paramTypeRefGenericDOMapper;

    @Override
    public ParamTypeRefGenericBO queryByParamAndGenericTypeId(Integer paramTypeId, Integer genericTypeId) {
        ParamTypeRefGenericDO paramTypeRefGenericDO=paramTypeRefGenericDOMapper.queryByParamAndGenericTypeId(paramTypeId,genericTypeId);
        if(paramTypeRefGenericDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(paramTypeRefGenericDO,ParamTypeRefGenericBO.class);
    }

    @Override
    public ParamTypeRefGenericBO persist(Integer paramTypeId, Integer genericTypeId) {
        ParamTypeRefGenericDO paramTypeRefGenericDO=new ParamTypeRefGenericDO();
        paramTypeRefGenericDO.setGenericTypeId(genericTypeId);
        paramTypeRefGenericDO.setParamTypeId(paramTypeId);
        paramTypeRefGenericDOMapper.persist(paramTypeRefGenericDO);
        return BeanCopierUtils.copyOne2One(paramTypeRefGenericDO,ParamTypeRefGenericBO.class);
    }

    @Override
    public void deleteByParamTypeId(Integer id) {
        paramTypeRefGenericDOMapper.deleteById(id);
    }
}
