package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.InterfaceCatagoryDOMapper;
import com.gitee.itapm.mapper.bean.InterfaceCatagoryDO;
import com.gitee.itapm.service.InterfaceCatagoryService;
import com.gitee.itapm.service.bean.InterfaceCatagoryBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by jetty on 2018/12/12.
 */
@Component
public class InterfaceCatagoryServiceImpl implements InterfaceCatagoryService {


    @Autowired
    private InterfaceCatagoryDOMapper interfaceCatagoryDOMapper;

    @Override
    public InterfaceCatagoryBO queryBySystemVersionIdAndName(Integer systemVersionId, String name) {
        InterfaceCatagoryDO interfaceCatagoryDO=interfaceCatagoryDOMapper.queryBySystemVersionIdAndName(systemVersionId, name);
        if(interfaceCatagoryDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(interfaceCatagoryDO,InterfaceCatagoryBO.class);
    }

    @Override
    public InterfaceCatagoryBO persist(Integer systemVersionId, String name) {
        InterfaceCatagoryDO interfaceCatagoryDO=new InterfaceCatagoryDO();
        interfaceCatagoryDO.setSystemVersionId(systemVersionId);
        interfaceCatagoryDO.setName(name);
        interfaceCatagoryDO.setStatus("ONLINE");
        interfaceCatagoryDOMapper.insert(interfaceCatagoryDO);
        return BeanCopierUtils.copyOne2One(interfaceCatagoryDO,InterfaceCatagoryBO.class);
    }

    @Override
    public List<InterfaceCatagoryBO> queryBySystemVersionId(Integer systemVersionId) {
        List<InterfaceCatagoryDO> interfaceCatagoryDOList= interfaceCatagoryDOMapper.queryBySystemVersionId(systemVersionId);
        if(CollectionUtils.isEmpty(interfaceCatagoryDOList)){
            return Collections.EMPTY_LIST;
        }
        return BeanCopierUtils.copyList2List(interfaceCatagoryDOList, InterfaceCatagoryBO.class);
    }

    @Override
    public void deleteById(Integer id) {
        interfaceCatagoryDOMapper.deleteById(id);
    }
}
