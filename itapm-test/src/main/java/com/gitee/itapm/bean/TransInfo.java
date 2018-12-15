package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.ApiParam;
import com.gitee.itapm.annotations.enums.Required;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by jetty on 2018/12/15.
 */
@Data
public class TransInfo {

    @ApiParam(length = 64,required= Required.Y,desrciption = "姓名")
    private String name;
    @ApiParam(length = 64,desrciption = "从哪里来")
    private String from;
    @ApiParam(length = 64,desrciption = "去哪里",required= Required.Y)
    private String to;
    @ApiParam(length = 64,desrciption = "交易金额",required= Required.Y)
    private BigDecimal transAmt;

}
