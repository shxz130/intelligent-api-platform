package com.gitee.itapm.core.handle.parent;

import com.gitee.itapm.service.bean.parent.BaseBO;

/**
 * Created by jetty on 2018/12/9.
 */
public interface Handler<T extends BaseBO,F extends BaseBO> {

    public void handle(T request,F response);

}
