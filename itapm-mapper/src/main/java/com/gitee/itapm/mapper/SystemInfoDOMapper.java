package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.SystemInfoDO;
import com.gitee.itapm.mapper.constants.SystemInfoSQLConstants;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jetty on 2018/12/9.
 */
@Mapper
public interface SystemInfoDOMapper {

    @Insert(SystemInfoSQLConstants.INSERT_SQL)
    public Integer insert(SystemInfoDO systemInfoDO);



    @Select(SystemInfoSQLConstants.QUERY_BY_NAME)
    public SystemInfoDO queryByEnName(@Param("enName")String enName);

}
