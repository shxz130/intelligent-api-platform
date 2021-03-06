package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.InterfaceCatagoryDO;
import com.gitee.itapm.mapper.constants.InterfaceCatagorySQLConstants;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jetty on 2018/12/9.
 */
public interface InterfaceCatagoryDOMapper {


    @Insert(InterfaceCatagorySQLConstants.INSERT_SQL)
    @Options(useGeneratedKeys=true, keyProperty="id")
    public Integer insert(InterfaceCatagoryDO interfaceCatagoryDO);


    @Select(InterfaceCatagorySQLConstants.QUERY_BY_SYSTEM_VERSION_ID_NAME)
    InterfaceCatagoryDO queryBySystemVersionIdAndName(@Param("systemVersionId")Integer systemVersionId, @Param("name")String name);


    @Select(InterfaceCatagorySQLConstants.QUERY_BY_SYSTEM_VERSION_ID)
    List<InterfaceCatagoryDO> queryBySystemVersionId(@Param("systemVersionId")Integer systemVersionId);


    @Delete(InterfaceCatagorySQLConstants.DELETE_BY_ID)
    Integer deleteById(@Param("id")Integer id);
}
