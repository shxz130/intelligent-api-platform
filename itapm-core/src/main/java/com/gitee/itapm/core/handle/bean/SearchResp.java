package com.gitee.itapm.core.handle.bean;

import com.gitee.itapm.service.bean.InterfaceDetailBO;
import com.gitee.itapm.service.bean.SystemInfoBO;
import com.gitee.itapm.service.bean.parent.BaseBO;
import com.gitee.itapm.utils.page.PageBean;
import lombok.Data;

import java.util.List;

/**
 * Created by jetty on 2018/12/9.
 */
@Data
public class SearchResp extends BaseBO{

    private PageBean<InterfaceDetailBO> pageBean;

    private List<SystemInfoBO> systemInfoList;
}
