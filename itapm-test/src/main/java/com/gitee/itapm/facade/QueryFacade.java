package com.gitee.itapm.facade;

import com.gitee.itapm.annotations.Catagory;
import com.gitee.itapm.annotations.DubboApi;
import com.gitee.itapm.bean.QueryReq;
import com.gitee.itapm.bean.QueryResp;

/**
 * Created by jetty on 2018/12/15.
 */
@Catagory("ITAPM系统查询类接口")
public interface QueryFacade {

    @DubboApi(description = "查询交易接口2",memo = "用来查询所有相关交易信息3")
    QueryResp queryTranInfo(QueryReq queryReq);
}


