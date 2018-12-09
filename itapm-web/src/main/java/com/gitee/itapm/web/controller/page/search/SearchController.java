package com.gitee.itapm.web.controller.page.search;

import com.gitee.itapm.core.handle.bean.SearchResp;
import com.gitee.itapm.core.handle.search.SearchHandler;
import com.gitee.itapm.web.controller.bean.Search;
import com.gitee.itapm.web.controller.bean.convert.BOConvert2ModelMap;
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


    /**
     * 查询页面
     *
     * @param search
     * @param model
     * @return
     */
    @PostMapping(UrlPathConstants.ITAPM_GO_SEARCH)
    public String search(Search search,Model model){
        SearchResp searchResp=new SearchResp();
        searchHandler.handle(DTOConvert2BO.convert2SearchBO(search), searchResp);
        model.addAllAttributes(BOConvert2ModelMap.convertSearchResp(searchResp));
        return TemplatePathConstants.SEARCH;
    }


    @GetMapping(UrlPathConstants.ITAPM_GO_SEARCH)
    public String getSearch(Search search,Model model){
        SearchResp searchResp=new SearchResp();
        searchHandler.handle(DTOConvert2BO.convert2SearchBO(search), searchResp);
        model.addAllAttributes(BOConvert2ModelMap.convertSearchResp(searchResp));
        return TemplatePathConstants.SEARCH;
    }
}
