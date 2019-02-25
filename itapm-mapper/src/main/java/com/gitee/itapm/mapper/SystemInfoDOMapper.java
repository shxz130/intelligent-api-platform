package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.SystemInfoDO;
import com.gitee.itapm.mapper.constants.SystemInfoSQLConstants;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jetty on 2018/12/9.
 */
@Mapper
public interface SystemInfoDOMapper {

    @Insert(SystemInfoSQLConstants.INSERT_SQL)
    @Options(useGeneratedKeys=true, keyProperty="id")
    public Integer persist(SystemInfoDO systemInfoDO);

    @Select(SystemInfoSQLConstants.QUERY_BY_NAME)
    public SystemInfoDO queryByEnName(@Param("enName")String enName);

    @Update(SystemInfoSQLConstants.UPDATE_BY_ID)
    public void updateById(SystemInfoDO systemInfoDO);

    @Select(SystemInfoSQLConstants.QUERY_ALL)
    List<SystemInfoDO> queryAllList();

    @Delete(SystemInfoSQLConstants.DELETE_BY_ID)
    void deleteById(@Param("id")Integer id);
}
