package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.ApiParam;
import com.gitee.itapm.annotations.enums.Required;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created by jetty on 2018/12/11.
 */
@Data
@ToString(exclude = {"testBeanList"})
public class TestBean extends AbstractBean {

    @ApiParam(length = 3,required= Required.Y,defaultValue = "Y",example="11",desrciption="这个是数字")
    private int num;

    @ApiParam(length = 4,required= Required.Y,defaultValue = "Y",example="11",desrciption = "这个是字母")
    private boolean a;

    @ApiParam(length = 4,required= Required.N,defaultValue = "Y",example="11",desrciption = "List")
    private List<AbstractBean> abstractBeanList;

    @ApiParam(length = 3,required= Required.N,defaultValue = "Y",example="11",desrciption = "arge")
    private String[] arge;

    @ApiParam(length = 3,required= Required.N,defaultValue = "Y",example="11",desrciption = "testBeanList")
    private List<TestBean> testBeanList;

    @ApiParam(length = 88,required= Required.Y,defaultValue = "Y",example="11",desrciption = "modelAList")
    private List<ModelA> modelAList;


    @ApiParam(length = 88,required= Required.Y,defaultValue = "Y",example="11",desrciption = "modelAList")
    private Map<String,ModelA> modelAMap;

}
