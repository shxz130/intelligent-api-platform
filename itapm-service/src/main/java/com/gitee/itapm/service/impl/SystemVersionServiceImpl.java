package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.SystemVersionDOMapper;
import com.gitee.itapm.service.SystemVersionService;
import com.gitee.itapm.service.bean.SystemVersionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class SystemVersionServiceImpl implements SystemVersionService {

    @Autowired
    private SystemVersionDOMapper systemVersionDOMapper;

    @Override
    public SystemVersionBO queryBySystemInfoIdAndVersion(Integer systemId, String version) {
        return null;
    }

    @Override
    public SystemVersionBO persist(Integer systemId, String systemEnName, String version) {
        return null;
    }
}
