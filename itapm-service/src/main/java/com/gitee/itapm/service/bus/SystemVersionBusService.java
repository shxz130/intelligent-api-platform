package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.SystemVersionService;
import com.gitee.itapm.service.bean.SystemInfoBO;
import com.gitee.itapm.service.bean.SystemVersionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/12.
 */
@Component
public class SystemVersionBusService {

    @Autowired
    private SystemVersionService systemVersionService;


    public synchronized SystemVersionBO persist(SystemInfoBO systemInfoBO,String version){
        SystemVersionBO systemVersionBO= systemVersionService.queryBySystemInfoIdAndVersion(systemInfoBO.getId(), version);
        if(systemInfoBO==null){
            systemVersionBO=systemVersionService.persist( systemInfoBO.getId(),systemInfoBO.getEnName(), version);
        }
        return systemVersionBO;
    }



}
