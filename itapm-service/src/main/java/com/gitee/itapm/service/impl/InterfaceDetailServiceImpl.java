package com.gitee.itapm.service.impl;

import com.gitee.itapm.service.InterfaceDetailService;
import com.gitee.itapm.service.bean.InterfaceDetailBO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class InterfaceDetailServiceImpl implements InterfaceDetailService{

    @Override
    public InterfaceDetailBO persist(InterfaceDetailBO interfaceDetailBO) {
        return null;
    }

    @Override
    public InterfaceDetailBO queryBySystemVersionIdAndName(Integer systemVersionId, String name) {
        return null;
    }

    @Override
    public void updateById(InterfaceDetailBO interfaceDetailBO) {

    }

    @Override
    public List<InterfaceDetailBO> queryByCatagoryId(Integer catagoryId) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
