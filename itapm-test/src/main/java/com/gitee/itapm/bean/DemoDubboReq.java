package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.ApiParam;
import com.gitee.itapm.annotations.enums.Required;
import lombok.Data;

import java.util.List;

/**
 * Created by jetty on 2018/12/12.
 */
@Data
public class DemoDubboReq {

    @ApiParam(length = 3,required= Required.Y,defaultValue = "Y",example="11",desrciption="名称")
    private String name;
    @ApiParam(length = 3,required= Required.Y,defaultValue = "Y",example="11",desrciption="这个是数字")
    private int age;
    @ApiParam(length = 88,required= Required.Y,defaultValue = "Y",example="11",desrciption = "modelAList")
    private List<ModelA> modelAList;



}
