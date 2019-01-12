package com.gitee.itapm.rest;

import com.gitee.itapm.annotations.Catagory;
import com.gitee.itapm.annotations.RestApi;
import com.gitee.itapm.bean.BaseResp;
import com.gitee.itapm.bean.RestResp;
import com.gitee.itapm.bean.TransInfo;
import com.gitee.itapm.utils.page.ResultBean;
import com.gitee.itapm.web.controller.bean.test.TestRequest;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

/**
 * Created by jetty on 2018/12/27.
 */
@RestController
public class TestController {

    @GetMapping(UrlPathConstants.ITAPM_TEST_GET)
    @Catagory("测试")
    @RestApi(description = "get方法测试")
    public BaseResp<TransInfo> get(TestRequest testRequest){
        return new BaseResp<TransInfo>();
    }


    @PostMapping(UrlPathConstants.ITAPM_TEST_POST)
    @Catagory("测试")
    @RestApi(description = "post方法测试")
    public BaseResp<TransInfo> post(TestRequest testRequest){
        return new RestResp();
    }
}
