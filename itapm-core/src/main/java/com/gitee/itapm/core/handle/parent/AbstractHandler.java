package com.gitee.itapm.core.handle.parent;

import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jetty on 2018/12/9.
 */
@Slf4j
public abstract class AbstractHandler<T extends BaseBO,F extends BaseBO> implements Handler<T,F>{

    @Override
    public void handle(T request, F response) {
        long start=System.currentTimeMillis();
        log.info("[{}],开始处理请求，请求参数[{}]",this.getClass(),request);
        Map contextMap=new HashMap<>(8);
        check(request,response,contextMap);
        before(request,response, contextMap);
        doHandle(request,response, contextMap);
        after(request,response,contextMap);
        log.info("[{}],请求结束，耗时{}ms",this.getClass(),response);
    }

    protected void check(T request, F response,Map map){

    }

    protected void before(T request, F response,Map map){

    }

    protected void after(T request, F response,Map map){

    }

    protected abstract void doHandle(T request, F response,Map map);
}
