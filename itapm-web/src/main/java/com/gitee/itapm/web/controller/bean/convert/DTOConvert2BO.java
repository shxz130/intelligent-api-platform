package com.gitee.itapm.web.controller.bean.convert;

import com.gitee.itapm.core.handle.bean.SearchReq;
import com.gitee.itapm.web.controller.bean.Search;

/**
 * Created by jetty on 2018/12/11.
 */
public class DTOConvert2BO {

    public static SearchReq convert2SearchBO(Search search){

        return new SearchReq();
    }
}
