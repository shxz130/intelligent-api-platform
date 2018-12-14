package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.ParamFieldDO;
import com.gitee.itapm.mapper.constants.ParamFieldSQLConstants;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamFieldDOMapper {


    @Select(ParamFieldSQLConstants.QUERY_BY_PARAM_TYPE_PARAM_NAME)
    public ParamFieldDO queryByParamTypeIdAndParamName(@Param("paramTypeId")Integer paramTypeId,@Param("paramName") String name);

    @Select(ParamFieldSQLConstants.QUERY_BY_PARAM_TYPE_ID)
    List<ParamFieldDO> queryByParamTypeId(@Param("paramTypeId")Integer paramTypeId);

    @Delete(ParamFieldSQLConstants.DELETE_BY_ID)
    Integer deleteById(@Param("id")Integer id);

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Select(ParamFieldSQLConstants.PERSIST)
    void persist(ParamFieldDO paramFieldDO);

    @Select(ParamFieldSQLConstants.UPDATE_BY_ID)
    Integer updateById(ParamFieldDO paramFieldDO);
}
