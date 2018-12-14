package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.ParamFieldDO;
import com.gitee.itapm.mapper.constants.ParamFieldSQLConstants;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamFieldDOMapper {


    @Select(ParamFieldSQLConstants.QUERY_BY_PARAM_TYPE_PARAM_NAME)
    public ParamFieldDO queryByParamTypeIdAndParamName(Integer paramTypeId, String name);

    @Select(ParamFieldSQLConstants.QUERY_BY_PARAM_TYPE_ID)
    List<ParamFieldDO> queryByParamTypeId(Integer paramTypeId);

    @Delete(ParamFieldSQLConstants.DELETE_BY_ID)
    Integer deleteById(Integer id);

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Select(ParamFieldSQLConstants.PERSIST)
    void persist(ParamFieldDO paramFieldDO);

    @Select(ParamFieldSQLConstants.UPDATE_BY_ID)
    Integer updateById(ParamFieldDO paramFieldDO);
}
