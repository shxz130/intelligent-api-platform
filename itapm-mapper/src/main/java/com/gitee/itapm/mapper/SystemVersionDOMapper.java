package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.SystemVersionDO;
import com.gitee.itapm.mapper.constants.SystemVersionSQLConstants;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface SystemVersionDOMapper {

    @Select(SystemVersionSQLConstants.QUERY_BY_STSTEM_INFO_ID_AND_VERSION)
    public SystemVersionDO queryBySystemInfoIdAndVersion(Integer systemId, String version);


    @Insert(SystemVersionSQLConstants.PERSIST)
    @Options(useGeneratedKeys=true, keyProperty="id")
    public void persist(SystemVersionDO systemVersionDO);
}
