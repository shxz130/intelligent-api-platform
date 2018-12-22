package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.ApiParam;

import javax.xml.crypto.Data;

/**
 * Created by jetty on 2018/12/22.
 */
public class BaseResp<T> {

    @ApiParam
    private T data;
}
