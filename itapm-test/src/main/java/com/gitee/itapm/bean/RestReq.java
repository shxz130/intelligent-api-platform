package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.ApiParam;
import lombok.Data;

/**
 * Created by jetty on 2018/12/22.
 */
@Data
public class RestReq {

    @ApiParam
    String name;
    @ApiParam
    String data;
}
