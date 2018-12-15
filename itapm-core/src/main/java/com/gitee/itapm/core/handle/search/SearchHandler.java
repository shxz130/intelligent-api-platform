package com.gitee.itapm.core.handle.search;

import cn.hutool.db.PageResult;
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
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        SystemVersionBO systemVersionBO=systemVersionBusService.queryLastOneBySystemInfoId(request.getSystemId());
        List<InterfaceDetailBO> interfaceDetailBOList=null;
        if(StringUtils.isBlank(request.getSearchKey())){
            interfaceDetailBOList = interfaceDetailBusService.queryBySystemVersionId(systemVersionBO.getId());
        }else{
            interfaceDetailBOList = interfaceDetailBusService.queryBySystemVersionIdAndCondition(systemVersionBO.getId(),request.getSearchKey());
        }
        Integer pageCount=PageHelper.getLocalPage().getPages();
        response.setPageBean(buildPageBean(interfaceDetailBOList));

    }

    private PageBean<InterfaceDetailBO> buildPageBean(List<InterfaceDetailBO> interfaceDetailBOList){
        Integer pageCount=PageHelper.getLocalPage().getPages();
        Integer pageSize=PageHelper.getLocalPage().getPageSize();
        Integer currentPage=PageHelper.getLocalPage().getPageNum();
        Integer totalCount=((Long)PageHelper.getLocalPage().getTotal()).intValue();
        PageBean<InterfaceDetailBO> pageBean=new PageBean<InterfaceDetailBO>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(pageCount);
        pageBean.setTotalCount(totalCount);
        pageBean.setDataList(interfaceDetailBOList);
        return pageBean;
    }
}
