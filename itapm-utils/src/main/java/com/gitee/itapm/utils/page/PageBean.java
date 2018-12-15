package com.gitee.itapm.utils.page;

import lombok.Data;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

/**
 * Created by jetty on 2018/12/15.
 */
@Data
public class PageBean<T> {

    private Integer currentPage;

    private Integer pageSize;

    private Integer totalCount;

    private Integer totalPage;

    private List<T> dataList;
}
