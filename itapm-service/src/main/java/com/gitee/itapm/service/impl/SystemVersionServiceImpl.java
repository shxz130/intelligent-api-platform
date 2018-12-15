package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.SystemVersionDOMapper;
import com.gitee.itapm.mapper.bean.SystemInfoDO;
import com.gitee.itapm.mapper.bean.SystemVersionDO;
import com.gitee.itapm.service.SystemVersionService;
import com.gitee.itapm.service.bean.SystemVersionBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
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
        SystemVersionDO systemVersionDO= systemVersionDOMapper.queryBySystemInfoIdAndVersion(systemId, version);
        if(systemVersionDO==null){
            return null;
        }else{
            return BeanCopierUtils.copyOne2One(systemVersionDO,SystemVersionBO.class);
        }
    }

    @Override
    public SystemVersionBO persist(Integer systemId, String systemEnName, String version) {
        SystemVersionDO systemVersionDO=new SystemVersionDO();
        systemVersionDO.setSystemId(systemId);
        systemVersionDO.setSystemName(systemEnName);
        systemVersionDO.setSystemVersion(version);
        systemVersionDOMapper.persist(systemVersionDO);
        return BeanCopierUtils.copyOne2One(systemVersionDO,SystemVersionBO.class);
    }


    @Override
    public SystemVersionBO queryLastOneBySystemInfoId(Integer systemInfoId) {
        SystemVersionDO  systemVersionDO=systemVersionDOMapper.queryLastOneBySystemInfoId(systemInfoId);
        if(systemVersionDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(systemVersionDO,SystemVersionBO.class);
    }
}
