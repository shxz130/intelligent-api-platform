package com.gitee.itapm.web.controller.bean;

import com.gitee.itapm.core.task.Excutor;
import com.gitee.itapm.paser.bean.Document;
import com.gitee.itapm.utils.page.ResultBean;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jetty on 2018/12/11.
 */

@RestController
public class PluginApiController {

    @PostMapping(UrlPathConstants.ITAPM_API_PUSH_URL)
    public ResultBean apiPush(Document document){
        Excutor.submit(document);
        return new ResultBean();
    }


}
