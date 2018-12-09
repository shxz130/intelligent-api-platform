package com.gitee.itapm.web.controller.page.login;

import com.gitee.itapm.web.controller.bean.User;
import com.gitee.itapm.web.utils.TemplatePathConstants;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jetty on 2018/12/9.
 */
@Controller
public class LoginController {

    @RequestMapping(value = UrlPathConstants.ITAPM_GO_LOGIN,method={RequestMethod.GET})
    public String login(User user){
        return TemplatePathConstants.LOGIN_PAGE;
    }
}
