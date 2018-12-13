package com.gitee.itapm.service;

import com.gitee.itapm.service.bean.InterfaceCatagoryBO;

/**
 * Created by jetty on 2018/12/12.
 */

public interface InterfaceCatagoryService {


    InterfaceCatagoryBO queryBySystemVersionIdAndName(Integer systemVersionId,String name);

    InterfaceCatagoryBO persist(Integer systemVersionId,String name);


}
