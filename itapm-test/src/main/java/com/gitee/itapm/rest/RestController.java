package com.gitee.itapm.rest;

import com.gitee.itapm.annotations.Catagory;
import com.gitee.itapm.annotations.RestApi;
import com.gitee.itapm.bean.RestReq;
import com.gitee.itapm.bean.RestResp;
import com.gitee.itapm.bean.RestResult;
import com.gitee.itapm.core.handle.bean.IndexResp;
import com.gitee.itapm.web.utils.TemplatePathConstants;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jetty on 2018/12/22.
 */
@Catagory("rest接口")
@Controller
public class RestController  {


    @RestApi(description = "c测试RequestMapping")
    @RequestMapping(value = UrlPathConstants.ITAPM_GO_INDEX,method={RequestMethod.GET,RequestMethod.POST})
    public RestResult rest1(RestReq restReq,Model model){
        return null;
    }

    @RestApi(description = "c测试GetMapping")
    @GetMapping(UrlPathConstants.ITAPM_GO_LOGIN)
    public RestResult rest2(RestReq restReq,Model model){
        return null;
    }

    @RestApi(description = "测试PostMapping")
    @PostMapping(UrlPathConstants.ITAPM_GO_SEARCH)
    public RestResult rest3(RestReq restReq,Model model){
        return null;
    }

    @RestApi(description = "没有请求参数的接口")
    @PostMapping(UrlPathConstants.ITAPM_GO_SEARCH)
    public RestResult rest4(Model model){
        return null;
    }
}
