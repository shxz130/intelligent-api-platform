package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.SystemVersionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface SystemVersionDOMapper {


    public SystemVersionDO queryBySystemInfoIdAndVersion(Integer systemId, String version);


    public void persist(SystemVersionDO systemVersionDO);
}
