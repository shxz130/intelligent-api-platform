package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.SystemInfoService;
import com.gitee.itapm.service.bean.SystemInfoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/12.
 */
@Component
public class SystemInfoBusService {

    @Autowired
    private SystemInfoService systemInfoService;


   public synchronized SystemInfoBO persist(String systemEnName,String systemChName){
        SystemInfoBO systemInfoBO=systemInfoService.queryByEnName(systemEnName);
        if(systemInfoBO==null){
            systemInfoBO = systemInfoService.persist(systemEnName,systemChName);
        }
       return systemInfoBO;
   }
}
