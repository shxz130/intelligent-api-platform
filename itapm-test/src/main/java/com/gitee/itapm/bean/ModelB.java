package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.ApiParam;
import com.gitee.itapm.annotations.enums.Required;

/**
 * Created by jetty on 2018/12/11.
 */
public class ModelB {

    @ApiParam(length = 4,required= Required.N,defaultValue = "Y",example="11",desrciption = "modelB的name")
    private String name;
}
