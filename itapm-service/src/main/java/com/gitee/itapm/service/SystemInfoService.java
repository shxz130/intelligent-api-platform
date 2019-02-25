package com.gitee.itapm.service;

import com.gitee.itapm.service.bean.SystemInfoBO;

import java.util.List;

/**
 * Created by jetty on 2018/12/11.
 */
public interface SystemInfoService {


    public SystemInfoBO persist(String enName,String chName);

    public SystemInfoBO queryByEnName(String enName);

    public void updateById(SystemInfoBO systemInfoBO);

    List<SystemInfoBO> queryAllList();

    void deleteById(Integer id);
}
