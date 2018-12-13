package com.gitee.itapm.service.impl;

import com.gitee.itapm.service.InterfaceCatagoryService;
import com.gitee.itapm.service.bean.InterfaceCatagoryBO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jetty on 2018/12/12.
 */
@Component
public class InterfaceCatagoryServiceImpl implements InterfaceCatagoryService {

    @Override
    public InterfaceCatagoryBO queryBySystemVersionIdAndName(Integer systemVersionId, String name) {
        return null;
    }

    @Override
    public InterfaceCatagoryBO persist(Integer systemVersionId, String name) {
        return null;
    }

    @Override
    public List<InterfaceCatagoryBO> queryBySystemVersionId(Integer systemVersionId) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
