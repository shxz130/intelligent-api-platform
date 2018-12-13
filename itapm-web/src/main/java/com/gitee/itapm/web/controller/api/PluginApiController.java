package com.gitee.itapm.web.controller.api;

import com.alibaba.fastjson.JSON;
import com.gitee.itapm.core.task.Excutor;
import com.gitee.itapm.paser.bean.Document;
import com.gitee.itapm.utils.page.ResultBean;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jetty on 2018/12/11.
 */

@RestController
public class PluginApiController {

    @PostMapping(UrlPathConstants.ITAPM_API_PUSH_URL)
    public ResultBean apiPush(@RequestBody Document document,HttpServletRequest request){
        Excutor.submit(document);
        return new ResultBean();
    }


}
