package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.InterfaceDetailDO;
import com.gitee.itapm.mapper.constants.InterfaceDetailSQLConstants;
import com.gitee.itapm.mapper.constants.ParamInfoSQLConstants;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jetty on 2018/12/9.
 */
public interface InterfaceDetailDOMapper {

    @Insert(InterfaceDetailSQLConstants.INSERT_SQL)
    @Options(useGeneratedKeys=true, keyProperty="id")
    public Integer persist(InterfaceDetailDO interfaceDetailDO);

    @Select(InterfaceDetailSQLConstants.QUERY_BY_ID)
    public InterfaceDetailDO queryById(@Param("id")Integer id);

    @Select(InterfaceDetailSQLConstants.QUERY_BY_SYSTEM_VERSION_ID)
    public List<InterfaceDetailDO> queryBySystemId(@Param("systemVersionId")Integer systemVersionId);

    @Select(InterfaceDetailSQLConstants.QUERY_BY_SYSTEM_VERSION_ID_CATAGORY_ID)
    public List<InterfaceDetailDO> queryBySystemIdCatagoryId(@Param("systemVersionId")Integer systemVersionId,@Param("catagoryId")Integer catagoryId);

    @Select(InterfaceDetailSQLConstants.QUERY_BY_SYSTEM_VERSION_ID_CATAGORY_ID_CONDITION)
    public List<InterfaceDetailDO> queryBySystemIdCatagoryIdAndCondition(@Param("systemVersionId")Integer systemVersionId,@Param("catagoryId")Integer catagoryId,@Param("condition")String condition);

    @Select(InterfaceDetailSQLConstants.QUERY_BY_SYSTEM_VERSION_ID_NAME)
    InterfaceDetailDO queryBySystemVersionIdAndName(@Param("systemVersionId")Integer systemVersionId,@Param("name") String name);

    @Update(InterfaceDetailSQLConstants.UPDATE_BY_ID)
    Integer updateById(InterfaceDetailDO interfaceDetailDO);

    @Select(InterfaceDetailSQLConstants.QUERY_BY_CATAGORY_ID)
    List<InterfaceDetailDO> queryByCatagoryId(@Param("catagoryId")Integer catagoryId);

    @Delete(InterfaceDetailSQLConstants.DELETE_BY_ID)
    InterfaceDetailDO deleteById(@Param("id")Integer id);
}
