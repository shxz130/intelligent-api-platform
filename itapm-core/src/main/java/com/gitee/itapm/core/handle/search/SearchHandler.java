package com.gitee.itapm.core.handle.search;

import com.gitee.itapm.core.handle.bean.SearchReq;
import com.gitee.itapm.core.handle.bean.SearchResp;
import com.gitee.itapm.core.handle.parent.AbstractHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by jetty on 2018/12/9.
 */
@Component
public class SearchHandler extends AbstractHandler<SearchReq,SearchResp> {

    @Override
    protected void doHandle(SearchReq request, SearchResp response, Map map) {

    }
}
