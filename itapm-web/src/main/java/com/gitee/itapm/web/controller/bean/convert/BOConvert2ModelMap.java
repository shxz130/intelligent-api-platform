package com.gitee.itapm.web.controller.bean.convert;

import com.gitee.itapm.core.handle.bean.SearchResp;
import com.gitee.itapm.web.controller.bean.Search;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by jetty on 2018/12/9.
 */
public class BOConvert2ModelMap {

    public static Map<String,Object> convertSearchResp(SearchResp searchResp){
        Map<String,Object> resultMap=new HashMap<>();
        if(searchResp==null){
            return resultMap;
        }
        return resultMap;
    }
}
