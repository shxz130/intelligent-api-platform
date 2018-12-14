package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.InterfaceCatagoryDO;
import com.gitee.itapm.mapper.constants.InterfaceCatagorySQLConstants;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jetty on 2018/12/9.
 */
public interface InterfaceCatagoryDOMapper {


    @Insert(InterfaceCatagorySQLConstants.INSERT_SQL)
    public Integer insert(InterfaceCatagoryDO interfaceCatagoryDO);

    @Select(InterfaceCatagorySQLConstants.QUERY_BY_SYSTEM_VERSION_ID_NAME)
    InterfaceCatagoryDO queryBySystemVersionIdAndName(Integer systemVersionId, String name);

    @Select(InterfaceCatagorySQLConstants.QUERY_BY_SYSTEM_VERSION_ID)
    List<InterfaceCatagoryDO> queryBySystemVersionId(Integer systemVersionId);

    @Delete(InterfaceCatagorySQLConstants.DELETE_BY_ID)
    Integer deleteById(Integer id);
}
