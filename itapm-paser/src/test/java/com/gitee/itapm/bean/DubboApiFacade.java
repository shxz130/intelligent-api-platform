package com.gitee.itapm.bean;

import com.gitee.itapm.annotations.Catagory;
import com.gitee.itapm.annotations.DubboApi;
import com.gitee.itapm.annotations.enums.InterfaceStatus;

/**
 * Created by jetty on 2018/12/12.
 */

@Catagory("Dubbo接口查询分类")
public interface DubboApiFacade {

    @DubboApi(status = InterfaceStatus.PRE_ONLINE,description="这是一个dubbo的查询接口",owner = "systemA",caller = "系统B",memo = "并不是每个接口都可以直接拿来用，这个就要自己来评估接口的性能")
    public DemoDubboResp queryDemo(DemoDubboReq req);
}
