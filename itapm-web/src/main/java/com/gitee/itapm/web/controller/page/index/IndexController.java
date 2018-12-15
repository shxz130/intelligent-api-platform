package com.gitee.itapm.web.controller.page.index;

import com.gitee.itapm.core.handle.bean.IndexResp;
import com.gitee.itapm.core.handle.index.IndexHandler;
import com.gitee.itapm.service.bean.parent.BaseBO;
import com.gitee.itapm.service.bus.SystemInfoBusService;
import com.gitee.itapm.web.controller.bean.User;
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
public class IndexController {


    @Autowired
    private IndexHandler indexHandler;

    @RequestMapping(value = UrlPathConstants.ITAPM_GO_INDEX,method={RequestMethod.GET})
    public String index(Model model){
        IndexResp indexResp=new IndexResp();
        indexHandler.handle(null, indexResp);
        model.addAttribute("systemInfoList",indexResp.getSystemCatagoryInterfaceDetailList());
        return TemplatePathConstants.INDEX;
    }
}
