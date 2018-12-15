package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.GenericParamFieldDO;
import com.gitee.itapm.mapper.bean.ParamFieldDO;
import com.gitee.itapm.mapper.constants.GenericParamFieldSQLConstants;
import com.gitee.itapm.mapper.constants.ParamFieldSQLConstants;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface GenericParamFieldDOMapper {


    @Select(GenericParamFieldSQLConstants.QUERY_BY_PARAM_TYPE_PARAM_NAME)
    public GenericParamFieldDO queryByParamTypeIdAndParamName(@Param("genericParamTypeId") Integer paramTypeId, @Param("paramName") String name);

    @Select(GenericParamFieldSQLConstants.QUERY_BY_PARAM_TYPE_ID)
    List<GenericParamFieldDO> queryByParamTypeId(@Param("genericParamTypeId") Integer paramTypeId);

    @Delete(GenericParamFieldSQLConstants.DELETE_BY_ID)
    Integer deleteById(@Param("id") Integer id);

    @Select(GenericParamFieldSQLConstants.PERSIST)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void persist(GenericParamFieldDO paramFieldDO);

    @Select(GenericParamFieldSQLConstants.UPDATE_BY_ID)
    Integer updateById(GenericParamFieldDO paramFieldDO);
}
