package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.ApiParam;
import com.gitee.itapm.annotations.enums.Required;
import lombok.Data;

/**
 * Created by jetty on 2018/12/11.
 */

@Data
public class ModelA {

    @ApiParam(length = 4,required= Required.N,defaultValue = "Y",example="11",desrciption = "modelA的A")
    private String age;

    @ApiParam(length = 4,required= Required.N,defaultValue = "Y",example="11",desrciption = "modelB")
    private ModelB modelB;

}
