package com.gitee.itapm.web.controller.page.search;

import com.gitee.itapm.core.handle.bean.SearchResp;
import com.gitee.itapm.core.handle.search.SearchHandler;
import com.gitee.itapm.web.controller.bean.Search;
import com.gitee.itapm.web.controller.bean.convert.DTOConvert2BO;
import com.gitee.itapm.web.utils.TemplatePathConstants;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by jetty on 2018/12/9.
 */
@Controller
public class SearchController {

    @Autowired
    private SearchHandler searchHandler;



    @GetMapping(UrlPathConstants.ITAPM_GO_SEARCH)
    public String searchPost(Search search,Model model){
        SearchResp searchResp=new SearchResp();
        searchHandler.handle(DTOConvert2BO.convert2SearchBO(search), searchResp);
        model.addAttribute("pageBean",searchResp.getPageBean());
        model.addAttribute("systemInfoList",searchResp.getSystemInfoList());
        model.addAttribute("search",search);
        return TemplatePathConstants.SEARCH;
    }
}
