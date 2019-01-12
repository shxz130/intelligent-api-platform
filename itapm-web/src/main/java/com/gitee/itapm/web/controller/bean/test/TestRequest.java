package com.gitee.itapm.web.controller.bean.test;

import com.gitee.itapm.annotations.ApiParam;
import com.gitee.itapm.annotations.enums.Required;
import lombok.Data;

import java.util.List;

/**
 * Created by jetty on 2018/12/27.
 */
@Data
public class TestRequest {

    @ApiParam(length = 4,required = Required.Y,defaultValue = "",desrciption = "姓名")
    private String name;

}
