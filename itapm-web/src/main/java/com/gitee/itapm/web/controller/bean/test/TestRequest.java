package com.gitee.itapm.web.controller.bean.test;

import com.gitee.itapm.annotations.ApiParam;
import lombok.Data;

/**
 * Created by jetty on 2018/12/27.
 */
@Data
public class TestRequest {

    @ApiParam
    private String name;
}
