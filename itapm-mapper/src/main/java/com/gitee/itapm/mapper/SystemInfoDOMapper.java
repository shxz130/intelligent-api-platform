package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.SystemInfoDO;
import com.gitee.itapm.mapper.constants.SystemInfoSQLConstants;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by jetty on 2018/12/9.
 */
@Mapper
public interface SystemInfoDOMapper {

    @Insert(SystemInfoSQLConstants.INSERT_SQL)
    public Integer insert(SystemInfoDO systemInfoDO);


}
