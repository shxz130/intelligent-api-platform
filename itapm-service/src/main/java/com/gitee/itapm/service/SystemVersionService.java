package com.gitee.itapm.service;

import com.gitee.itapm.service.bean.SystemInfoBO;
import com.gitee.itapm.service.bean.SystemVersionBO;

/**
 * Created by jetty on 2018/12/13.
 */
public interface SystemVersionService {

    public SystemVersionBO queryBySystemInfoIdAndVersion(Integer systemId,String version);

    public SystemVersionBO persist(Integer systemId,String systemEnName,String version);


    SystemVersionBO queryLastOneBySystemInfoId(Integer systemInfoId);

    void deleteById(Integer id);
}
