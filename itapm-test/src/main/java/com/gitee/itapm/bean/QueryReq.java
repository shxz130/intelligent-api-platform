package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.ApiParam;
import com.gitee.itapm.annotations.enums.Required;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jetty on 2018/12/12.
 */
@Data
public class QueryReq {

    @ApiParam(length = 64,required= Required.Y,defaultValue = "",example="I0003454645453423453",desrciption="名称")
    private String  transId;
    @ApiParam
    private List<TransInfo> reqSerial;


}
