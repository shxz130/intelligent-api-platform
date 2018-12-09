package com.gitee.itapm.core.handle.parent;

import com.gitee.itapm.core.handle.parent.bean.BaseBO;

/**
 * Created by jetty on 2018/12/9.
 */
public interface Handler<T extends BaseBO,F extends BaseBO> {

    public void handle(T request,F response);

}
