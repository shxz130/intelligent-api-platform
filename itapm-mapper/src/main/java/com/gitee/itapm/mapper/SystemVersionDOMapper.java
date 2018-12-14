package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.SystemVersionDO;

/**
 * Created by jetty on 2018/12/14.
 */
public interface SystemVersionDOMapper {

    public SystemVersionDO queryBySystemInfoIdAndVersion(Integer systemId, String version);


    public SystemVersionDO persist(Integer systemId, String systemEnName, String version);
}
