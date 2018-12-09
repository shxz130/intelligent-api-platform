package com.gitee.itapm.web.controller.page.index;

import com.gitee.itapm.web.controller.bean.User;
import com.gitee.itapm.web.utils.TemplatePathConstants;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jetty on 2018/12/9.
 */
@Controller
public class IndexController {


    @RequestMapping(value = UrlPathConstants.ITAPM_GO_INDEX,method={RequestMethod.GET})
    public String login(){
        return TemplatePathConstants.INDEX;
    }
}
