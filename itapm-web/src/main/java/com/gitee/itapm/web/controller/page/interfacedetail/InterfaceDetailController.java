package com.gitee.itapm.web.controller.page.interfacedetail;

import com.gitee.itapm.web.utils.TemplatePathConstants;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jetty on 2018/12/9.
 */
@Controller
public class InterfaceDetailController {

    @GetMapping(UrlPathConstants.ITAPM_GO_INTERFACE_DETAIL)
    public String goInterfaceDetailPage(Long id){
        return TemplatePathConstants.INTEFACE_DETAIL;
    }
}
