package com.gitee.itapm.core.handle.search;

import com.gitee.itapm.core.handle.bean.SearchReq;
import com.gitee.itapm.core.handle.bean.SearchResp;
import com.gitee.itapm.core.handle.parent.AbstractHandler;
import com.gitee.itapm.service.bean.InterfaceDetailBO;
import com.gitee.itapm.service.bean.SystemInfoBO;
import com.gitee.itapm.service.bean.SystemVersionBO;
import com.gitee.itapm.service.bus.InterfaceDetailBusService;
import com.gitee.itapm.service.bus.SystemInfoBusService;
import com.gitee.itapm.service.bus.SystemVersionBusService;
import com.gitee.itapm.utils.page.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by jetty on 2018/12/9.
 */
@Component
public class SearchHandler extends AbstractHandler<SearchReq,SearchResp> {

    @Autowired
    private SystemInfoBusService systemInfoBusService;
    @Autowired
    private SystemVersionBusService systemVersionBusService;
    @Autowired
    private InterfaceDetailBusService interfaceDetailBusService;

    @Override
    protected void doHandle(SearchReq request, SearchResp response, Map map) {

        List<SystemInfoBO> systemInfoBOList=systemInfoBusService.queryAllList();
        response.setSystemInfoList(systemInfoBOList);
        if(request.getSystemId()==null){
            return;
        }
        SystemVersionBO systemVersionBO=systemVersionBusService.queryLastOneBySystemInfoId(request.getSystemId());
        Page page=null;
        List<InterfaceDetailBO> interfaceDetailBOList=null;
        if(StringUtils.isBlank(request.getSearchKey())){
            page= PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
            interfaceDetailBOList = interfaceDetailBusService.queryBySystemVersionId(systemVersionBO.getId());
        }else{
            page=PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
            interfaceDetailBOList = interfaceDetailBusService.queryBySystemVersionIdAndCondition(systemVersionBO.getId(),request.getSearchKey());
        }
        response.setPageBean(buildPageBean(interfaceDetailBOList,page));

    }

    private PageBean<InterfaceDetailBO> buildPageBean(List<InterfaceDetailBO> interfaceDetailBOList,Page page){
        PageBean<InterfaceDetailBO> pageBean=new PageBean<InterfaceDetailBO>();
        pageBean.setCurrentPage(page.getPageNum());
        pageBean.setPageSize(page.getPageSize());
        pageBean.setTotalPage(page.getPages());
        pageBean.setTotalCount(((Long)page.getTotal()).intValue());
        pageBean.setDataList(interfaceDetailBOList);
        return pageBean;
    }
}
