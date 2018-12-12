package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.SystemInfoDOMapper;
import com.gitee.itapm.mapper.bean.SystemInfoDO;
import com.gitee.itapm.service.SystemInfoService;
import com.gitee.itapm.service.bean.SystemInfoBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 2018/12/11.
 */
@Component
public class SystemInfoServiceImpl implements SystemInfoService {

    @Autowired
    private SystemInfoDOMapper systemInfoDOMapper;

    @Override
    public SystemInfoBO queryByEnName(String enName) {
        SystemInfoDO systemInfoDO = systemInfoDOMapper.queryByEnName(enName);
        return BeanCopierUtils.copyOne2One(systemInfoDO,SystemInfoBO.class);
    }
}
