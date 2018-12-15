package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.InterfaceDetailDOMapper;
import com.gitee.itapm.mapper.bean.InterfaceDetailDO;
import com.gitee.itapm.mapper.bean.ParamFieldDO;
import com.gitee.itapm.service.InterfaceDetailService;
import com.gitee.itapm.service.bean.InterfaceDetailBO;
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
public class InterfaceDetailServiceImpl implements InterfaceDetailService{

    @Autowired
    private InterfaceDetailDOMapper interfaceDetailDOMapper;

    @Override
    public void persist(InterfaceDetailBO interfaceDetailBO) {
        InterfaceDetailDO interfaceDetailDO= BeanCopierUtils.copyOne2One(interfaceDetailBO, InterfaceDetailDO.class);
        interfaceDetailDOMapper.persist(interfaceDetailDO);
        interfaceDetailBO.setId(interfaceDetailDO.getId());

    }

    @Override
    public InterfaceDetailBO queryBySystemVersionIdAndName(Integer systemVersionId, String name) {
        InterfaceDetailDO interfaceDetailDO= interfaceDetailDOMapper.queryBySystemVersionIdAndName(systemVersionId, name);
        if(interfaceDetailDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(interfaceDetailDO,InterfaceDetailBO.class);
    }

    @Override
    public void updateById(InterfaceDetailBO interfaceDetailBO) {
        InterfaceDetailDO interfaceDetailDO= BeanCopierUtils.copyOne2One(interfaceDetailBO, InterfaceDetailDO.class);
        interfaceDetailDOMapper.updateById(interfaceDetailDO);
    }

    @Override
    public List<InterfaceDetailBO> queryByCatagoryId(Integer catagoryId) {
        List<InterfaceDetailDO> interfaceDetailDOList=interfaceDetailDOMapper.queryByCatagoryId(catagoryId);
        if(CollectionUtils.isEmpty(interfaceDetailDOList)){
            return Collections.emptyList();
        }
        return BeanCopierUtils.copyList2List(interfaceDetailDOList,InterfaceDetailBO.class);
    }

    @Override
    public void deleteById(Integer id) {
        interfaceDetailDOMapper.deleteById(id);
    }

    @Override
    public InterfaceDetailBO queryById(Integer interfaceDetailId) {
        InterfaceDetailDO interfaceDetailDO= interfaceDetailDOMapper.queryById(interfaceDetailId);
        if(interfaceDetailDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(interfaceDetailDO,InterfaceDetailBO.class);
    }

    public List<InterfaceDetailBO> queryBySystemVersionId(Integer systemVersionId) {
        List<InterfaceDetailDO> interfaceDetailList=interfaceDetailDOMapper.queryBySystemVersionId(systemVersionId);
        if(CollectionUtils.isEmpty(interfaceDetailList)){
            return Collections.emptyList();
        }
        return BeanCopierUtils.copyList2List(interfaceDetailList,InterfaceDetailBO.class);
    }

    public List<InterfaceDetailBO> queryBySystemVersionIdAndCondition(Integer systemVersionId, String searchKey) {
        List<InterfaceDetailDO> interfaceDetailList=interfaceDetailDOMapper.queryBySystemVersionIdAndCondition(systemVersionId,searchKey);
        if(CollectionUtils.isEmpty(interfaceDetailList)){
            return Collections.emptyList();
        }
        return BeanCopierUtils.copyList2List(interfaceDetailList,InterfaceDetailBO.class);
    }
}
