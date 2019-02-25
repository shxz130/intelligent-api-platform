package com.gitee.itapm.web.controller.page.backup;

import com.gitee.itapm.core.handle.bean.IndexResp;
import com.gitee.itapm.core.handle.index.IndexHandler;
import com.gitee.itapm.web.utils.TemplatePathConstants;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jetty on 2018/12/9.
 */
@Controller
public class BackUpController {

    @RequestMapping(value = UrlPathConstants.ITAPM_GO_DELETE_SYSTEM,method={RequestMethod.GET})
    public String goDeleteSystem(Model model){
        return TemplatePathConstants.BACKUP_DELETE_SYSTEM;
    }
}
