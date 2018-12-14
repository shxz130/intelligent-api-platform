package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.ParamTypeDOMapper;
import com.gitee.itapm.mapper.bean.ParamTypeDO;
import com.gitee.itapm.service.ParamTypeService;
import com.gitee.itapm.service.bean.ParamTypeBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/13.
 */

@Component
public class ParamTypeServiceImpl implements ParamTypeService{


    @Autowired
    private ParamTypeDOMapper paramTypeDOMapper;

    @Override
    public ParamTypeBO queryByInterfaceDetailIdNameAndResource(Integer interfaceDetailId, String name, String resource) {
         ParamTypeDO paramTypeDO=paramTypeDOMapper.queryByInterfaceDetailIdNameAndResource(interfaceDetailId, name, resource);
        if(paramTypeDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(paramTypeDO,ParamTypeBO.class);
    }

    @Override
    public ParamTypeBO persist(Integer interfaceDetailId, String name, String resource) {
        ParamTypeDO paramTypeDO=new ParamTypeDO();
        paramTypeDO.setInterfaceDetailId(interfaceDetailId);
        paramTypeDO.setParamTypeName(name);
        paramTypeDO.setResource(resource);
        paramTypeDOMapper.persist(paramTypeDO);
        return BeanCopierUtils.copyOne2One(paramTypeDO,ParamTypeBO.class);
    }
}
