package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.ApiParam;
import lombok.Data;

import java.util.List;

/**
 * Created by jetty on 2018/12/22.
 */
@Data
public class RestResult {

    @ApiParam
    private String name;
    @ApiParam(desrciption = "wosh3423;832432424io4qnfjskafafsdlfskkfdsakfdajsiifodlskafskdkfdsjsidskalsdkfjaskdaiodafksdafdka;fdklasjfdiadfjskfdsafjdaiodfkasdfjkafnsvaf;sdafksjfiaodisjfasl")
    private List<TransInfo> dataList;
    @ApiParam
    private TransInfo transInfo;
}
