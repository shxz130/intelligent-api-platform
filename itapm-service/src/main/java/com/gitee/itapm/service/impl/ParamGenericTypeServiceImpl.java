package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.ParamGenericTypeDOMapper;
import com.gitee.itapm.mapper.bean.ParamGenericTypeDO;
import com.gitee.itapm.service.ParamGenericTypeService;
import com.gitee.itapm.service.bean.GenericParamFieldBO;
import com.gitee.itapm.service.bean.ParamGenericTypeBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/13.
 */

@Component
public class ParamGenericTypeServiceImpl implements ParamGenericTypeService {


    @Autowired
    private ParamGenericTypeDOMapper paramGenericTypeDOMapper;

    @Override
    public ParamGenericTypeBO queryBySystemVersionIdAndName(Integer typeId, String name) {
        ParamGenericTypeDO paramGenericTypeDO= paramGenericTypeDOMapper.queryBySystemVersionIdAndName(typeId, name);
        if(paramGenericTypeDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(paramGenericTypeDO,ParamGenericTypeBO.class);
    }

    @Override
    public ParamGenericTypeBO persist(Integer systemVersionId, String name) {
        ParamGenericTypeDO paramGenericTypeDO=new ParamGenericTypeDO();
        paramGenericTypeDO.setName(name);
        paramGenericTypeDO.setSystemVersionId(systemVersionId);
        paramGenericTypeDOMapper.persist(paramGenericTypeDO);
        return BeanCopierUtils.copyOne2One(paramGenericTypeDO,ParamGenericTypeBO.class);
    }

    @Override
    public ParamGenericTypeBO queryById(Integer genericTypeId) {
        ParamGenericTypeDO paramGenericTypeDO= paramGenericTypeDOMapper.queryById(genericTypeId);
        if(paramGenericTypeDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(paramGenericTypeDO,ParamGenericTypeBO.class);

    }
}
