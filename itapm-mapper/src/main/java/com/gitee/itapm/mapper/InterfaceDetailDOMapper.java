package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.InterfaceDetailDO;
import com.gitee.itapm.mapper.constants.InterfaceDetailSQLConstants;
import com.gitee.itapm.mapper.constants.ParamInfoSQLConstants;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jetty on 2018/12/9.
 */
public interface InterfaceDetailDOMapper {

    @Insert(InterfaceDetailSQLConstants.INSERT_SQL)
    public Integer persist(InterfaceDetailDO interfaceDetailDO);

    @Select(InterfaceDetailSQLConstants.QUERY_BY_ID)
    public InterfaceDetailDO queryById(@Param("id")Integer id);

    @Select(InterfaceDetailSQLConstants.QUERY_BY_SYSTEM_ID)
    public List<InterfaceDetailDO> queryBySystemId(@Param("systemId")Integer systemId);

    @Select(InterfaceDetailSQLConstants.QUERY_BY_SYSTEM_ID_CATAGORY_ID)
    public List<InterfaceDetailDO> queryBySystemIdCatagoryId(@Param("systemId")Integer systemId,@Param("catagoryId")Integer catagoryId);

    @Select(InterfaceDetailSQLConstants.QUERY_BY_SYSTEM_ID_CATAGORY_ID_CONDITION)
    public List<InterfaceDetailDO> queryBySystemIdCatagoryIdAndCondition(@Param("systemId")Integer systemId,@Param("catagoryId")Integer catagoryId,@Param("condition")String condition);

    InterfaceDetailDO queryBySystemVersionIdAndName(Integer systemVersionId, String name);

    Integer updateById(InterfaceDetailDO interfaceDetailDO);

    List<InterfaceDetailDO> queryByCatagoryId(Integer catagoryId);

    InterfaceDetailDO deleteById(Integer id);
}
