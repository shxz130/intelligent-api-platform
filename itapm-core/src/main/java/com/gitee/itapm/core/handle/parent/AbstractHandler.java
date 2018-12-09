package com.gitee.itapm.core.handle.parent;

import com.gitee.itapm.core.handle.parent.bean.BaseBO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jetty on 2018/12/9.
 */
public abstract class AbstractHandler<T extends BaseBO,F extends BaseBO> implements Handler<T,F>{

    @Override
    public void handle(T request, F response) {
        Map contextMap=new HashMap<>(8);
        check(request,response,contextMap);
        before(request,response, contextMap);
        doHandle(request,response, contextMap);
        after(request,response,contextMap);
    }

    protected void check(T request, F response,Map map){

    }

    protected void before(T request, F response,Map map){

    }

    protected void after(T request, F response,Map map){

    }

    protected abstract void doHandle(T request, F response,Map map);
}
