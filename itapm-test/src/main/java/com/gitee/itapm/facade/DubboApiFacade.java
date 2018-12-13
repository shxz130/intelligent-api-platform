package com.gitee.itapm.facade;

import com.gitee.itapm.annotations.Catagory;
import com.gitee.itapm.annotations.DubboApi;
import com.gitee.itapm.annotations.enums.InterfaceStatus;
import com.gitee.itapm.bean.DemoDubboReq;
import com.gitee.itapm.bean.DemoDubboResp;

/**
 * Created by jetty on 2018/12/12.
 */

@Catagory("Dubbo接口查询分类")
public interface DubboApiFacade {

    @DubboApi(status = InterfaceStatus.PRE_ONLINE,description="这是个dubbo的查询接口",owner = "systemA",caller = "系统B",memo = "并不是每个接口都可以直接拿来用，这个就要自己来评估接口的性能")
    public DemoDubboResp queryDemo(DemoDubboReq req);

    @DubboApi(status = InterfaceStatus.PRE_ONLINE,description="这是d个dubbo的查询接口",owner = "systemA",caller = "系统B",memo = "并不是每个接口都可以直接拿来用，这个就要自己来评估接口的性能")
    public DemoDubboResp queryDemo1(DemoDubboReq req);

    @DubboApi(status = InterfaceStatus.PRE_ONLINE,description="这是一个dubbo的查询接口",owner = "systemB",caller = "系统B",memo = "并不是每个接口都可以直接拿来用，这个就要自己来评估接口的性能")
    @Catagory("接口分类")
    public DemoDubboResp queryDemo2(DemoDubboReq req);

    @DubboApi(status = InterfaceStatus.PRE_ONLINE,description="这是一个dubbo的查询接口",owner = "systemB",caller = "系统B",memo = "并不是每个接口都可以直接拿来用，这个就要自己来评估接口的性能")
    @Catagory("接口分类")
    public DemoDubboResp queryDemo3(DemoDubboReq req);
}
