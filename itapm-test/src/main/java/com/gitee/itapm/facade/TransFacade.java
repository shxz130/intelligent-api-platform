package com.gitee.itapm.facade;

import com.gitee.itapm.annotations.Catagory;
import com.gitee.itapm.annotations.DubboApi;
import com.gitee.itapm.bean.DemoDubboReq;
import com.gitee.itapm.bean.DemoDubboResp;
import com.gitee.itapm.bean.QueryReq;
import com.gitee.itapm.bean.QueryResp;

/**
 * Created by jetty on 2018/12/15.
 */
@Catagory("交易接口")
public interface TransFacade {


    @DubboApi(description = "交易接口",memo = "查询接口")
    DemoDubboResp purchase(DemoDubboReq queryReq);

}


