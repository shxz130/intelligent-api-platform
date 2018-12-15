package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.ParamFieldRefGenericDO;
import com.gitee.itapm.mapper.constants.ParamTypeRefGenericSQLConstants;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamFieldRefGenericDOMapper {


    @Select(ParamTypeRefGenericSQLConstants.QUERY_BY_PARAM_AND_GENERIC_TYPE_ID)
    ParamFieldRefGenericDO queryByParamAndGenericTypeId(@Param("paramFieldId")Integer paramFieldId, @Param("genericTypeId")Integer genericTypeId);

    @Insert(ParamTypeRefGenericSQLConstants.PERSIST)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void persist(ParamFieldRefGenericDO paramTypeRefGenericDO);

    @Delete(ParamTypeRefGenericSQLConstants.DELETE_BY_ID)
    Integer deleteById(@Param("id")Integer id);

    @Select(ParamTypeRefGenericSQLConstants.QUERY_BY_FIELD_ID)
    List<ParamFieldRefGenericDO> queryByFiledId(@Param("paramFieldId")Integer fieldId);
}
