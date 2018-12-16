package com.gitee.itapm.facade;

import com.gitee.itapm.annotations.Catagory;
import com.gitee.itapm.annotations.DubboApi;
import com.gitee.itapm.bean.QueryReq;
import com.gitee.itapm.bean.QueryResp;

/**
 * Created by jetty on 2018/12/15.
 */
@Catagory("查询接口")
public interface QueryFacade {


    @DubboApi(description = "查询交易接口1",memo = "查询接口1")
    QueryResp queryTranInfo(QueryReq queryReq);


    @DubboApi(description = "查询交易接口2",memo = "查询接口2")
    QueryResp queryTranInfo2(QueryReq queryReq);
}


