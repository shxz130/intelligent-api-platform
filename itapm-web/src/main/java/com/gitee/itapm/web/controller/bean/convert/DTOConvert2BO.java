package com.gitee.itapm.web.controller.bean.convert;

import com.gitee.itapm.core.handle.bean.SearchReq;
import com.gitee.itapm.web.controller.bean.Search;

/**
 * Created by jetty on 2018/12/11.
 */
public class DTOConvert2BO {

    public static SearchReq convert2SearchBO(Search search){
        SearchReq searchReq=new SearchReq();
        searchReq.setCurrentPage(search.getCurrentPage());
        searchReq.setPageSize(search.getPageSize());
        searchReq.setSystemId(search.getSystemId());
        searchReq.setSearchKey(search.getSearchKey());
        return searchReq;
    }
}
