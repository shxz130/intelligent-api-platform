package com.gitee.itapm.web.controller.page.interfacedetail;

import com.gitee.itapm.core.handle.bean.InterfaceDetailReq;
import com.gitee.itapm.core.handle.bean.InterfaceDetailResp;
import com.gitee.itapm.core.handle.interfacedetail.InterfaceDetailHandler;
import com.gitee.itapm.web.utils.TemplatePathConstants;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jetty on 2018/12/9.
 */
@Controller
public class InterfaceDetailController {

    @Autowired
    private InterfaceDetailHandler interfaceDetailHandler;

    @GetMapping(UrlPathConstants.ITAPM_GO_INTERFACE_DETAIL)
    public String goInterfaceDetailPage(Integer interfaceDetailId,Model model){
        InterfaceDetailResp interfaceDetailResp=new InterfaceDetailResp();
        interfaceDetailHandler.handle(new InterfaceDetailReq(interfaceDetailId),interfaceDetailResp);
        model.addAttribute("interfaceDetail",interfaceDetailResp.getInterfaceDetail());
        return TemplatePathConstants.INTEFACE_DETAIL;
    }
}
