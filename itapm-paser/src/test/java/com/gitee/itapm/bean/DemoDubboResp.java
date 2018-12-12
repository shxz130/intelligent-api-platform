package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.ApiParam;
import com.gitee.itapm.annotations.enums.Required;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by jetty on 2018/12/12.
 */
@Data
public class DemoDubboResp {
    @ApiParam
    private String code;
    @ApiParam
    private String memo;
    @ApiParam(length = 88,required= Required.Y,defaultValue = "Y",example="11",desrciption = "map")
    private Map<ModelA,ModelB> map;

    @ApiParam
    private List<String> listString;



}
