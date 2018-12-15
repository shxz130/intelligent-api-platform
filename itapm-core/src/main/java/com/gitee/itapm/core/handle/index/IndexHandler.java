package com.gitee.itapm.core.handle.index;

import com.gitee.itapm.core.handle.bean.Catagory;
import com.gitee.itapm.core.handle.bean.SystemCatagoryInterfaceDetail;
import com.gitee.itapm.core.handle.parent.AbstractHandler;
import com.gitee.itapm.service.bean.InterfaceCatagoryBO;
import com.gitee.itapm.service.bean.InterfaceDetailBO;
import com.gitee.itapm.service.bean.SystemInfoBO;
import com.gitee.itapm.service.bean.SystemVersionBO;
import com.gitee.itapm.service.bean.parent.BaseBO;
import com.gitee.itapm.core.handle.bean.IndexResp;

import com.gitee.itapm.service.bus.InterfaceCatagoryBusService;
import com.gitee.itapm.service.bus.InterfaceDetailBusService;
import com.gitee.itapm.service.bus.SystemInfoBusService;
import com.gitee.itapm.service.bus.SystemVersionBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jetty on 2018/12/15.
 */

@Component
public class IndexHandler extends AbstractHandler<BaseBO,IndexResp> {

    @Autowired
    private SystemInfoBusService systemInfoBusService;

    @Autowired
    private SystemVersionBusService systemVersionBusService;

    @Autowired
    private InterfaceCatagoryBusService interfaceCatagoryBusService;

    @Autowired
    private InterfaceDetailBusService interfaceDetailBusService;

    @Override
    protected void doHandle(BaseBO request, IndexResp response, Map map) {

        List<SystemCatagoryInterfaceDetail> allIndexList=new ArrayList<>();
        response.setSystemCatagoryInterfaceDetailList(allIndexList);
        List<SystemInfoBO> systemInfoBOList=systemInfoBusService.queryAllList();
        if(CollectionUtils.isEmpty(systemInfoBOList)){
            return ;
        }

        for(SystemInfoBO systemInfoBO:systemInfoBOList){
            SystemCatagoryInterfaceDetail systemCatagoryInterfaceDetail=new SystemCatagoryInterfaceDetail();
            systemCatagoryInterfaceDetail.setId(systemInfoBO.getId());
            systemCatagoryInterfaceDetail.setSystemEnName(systemInfoBO.getEnName());
            systemCatagoryInterfaceDetail.setSystemChName(systemInfoBO.getChName());
            SystemVersionBO systemVersionBO = systemVersionBusService.queryLastOneBySystemInfoId(systemInfoBO.getId());
            systemCatagoryInterfaceDetail.setCatagoryList(buildCatagoryList(systemVersionBO));
            allIndexList.add(systemCatagoryInterfaceDetail);
        }
    }

    private List<Catagory> buildCatagoryList(SystemVersionBO systemVersionBO) {
        List<Catagory> catagoryList=new ArrayList<>();
        List<InterfaceCatagoryBO>  interfaceCatagoryList=interfaceCatagoryBusService.queryBySystemVersionId(systemVersionBO.getId());
        for(InterfaceCatagoryBO catagoryBO: interfaceCatagoryList){
            Catagory catagory=new Catagory();
            catagory.setCatagoryName(catagoryBO.getName());
            catagory.setId(catagoryBO.getId());
            catagory.setInterfaceDetailBOList(buildInterfaceDetailBO(catagoryBO));
            catagoryList.add(catagory);
        }
        return catagoryList;
    }


    private List<InterfaceDetailBO> buildInterfaceDetailBO(InterfaceCatagoryBO catagoryBO) {
        List<InterfaceDetailBO> interfaceDetailBOList=new ArrayList<>();
        return interfaceDetailBusService.queryByCatagoryId(catagoryBO.getId());
    }
}
