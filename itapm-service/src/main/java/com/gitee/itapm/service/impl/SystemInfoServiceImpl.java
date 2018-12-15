package com.gitee.itapm.service.impl;

import com.gitee.itapm.mapper.SystemInfoDOMapper;
import com.gitee.itapm.mapper.bean.SystemInfoDO;
import com.gitee.itapm.service.SystemInfoService;
import com.gitee.itapm.service.bean.SystemInfoBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

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
        if(systemInfoDO==null){
            return null;
        }
        return BeanCopierUtils.copyOne2One(systemInfoDO,SystemInfoBO.class);
    }

    @Override
    public SystemInfoBO persist(String enName, String chName) {
        SystemInfoDO systemInfoDO=new SystemInfoDO();
        systemInfoDO.setEnName(enName);
        systemInfoDO.setChName(chName);
        systemInfoDOMapper.persist(systemInfoDO);
        return BeanCopierUtils.copyOne2One(systemInfoDO,SystemInfoBO.class);
    }

    @Override
    public void updateById(SystemInfoBO systemInfoBO) {
        SystemInfoDO systemInfoDO= BeanCopierUtils.copyOne2One(systemInfoBO,SystemInfoDO.class);
        systemInfoDOMapper.updateById(systemInfoDO);
    }

    @Override
    public List<SystemInfoBO> queryAllList() {
       List<SystemInfoDO> systemInfoDOList= systemInfoDOMapper.queryAllList();
       if(CollectionUtils.isEmpty(systemInfoDOList)){
           return Collections.emptyList();
       }
       return BeanCopierUtils.copyList2List(systemInfoDOList,SystemInfoBO.class);
    }
}
