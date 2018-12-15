package com.gitee.itapm.service;

import com.gitee.itapm.mapper.bean.InterfaceDetailDO;
import com.gitee.itapm.service.bean.InterfaceDetailBO;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
public interface InterfaceDetailService {

    public void persist(InterfaceDetailBO interfaceDetailBO);

    public InterfaceDetailBO queryBySystemVersionIdAndName(Integer systemVersionId,String name);

    public void updateById(InterfaceDetailBO interfaceDetailBO);

    public List<InterfaceDetailBO> queryByCatagoryId(Integer catagoryId);

    public void deleteById(Integer id);

    InterfaceDetailBO queryById(Integer interfaceDetailId);

    List<InterfaceDetailBO> queryBySystemVersionIdAndCondition(Integer systemVersionId,String searchKey);

    List<InterfaceDetailBO> queryBySystemVersionId(Integer systemVersionId);
}
