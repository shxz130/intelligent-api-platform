package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.GenericParamFieldRefGenericDO;
import com.gitee.itapm.mapper.bean.ParamFieldRefGenericDO;
import com.gitee.itapm.mapper.constants.GenericParamTypeRefGenericSQLConstants;
import com.gitee.itapm.mapper.constants.ParamTypeRefGenericSQLConstants;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface GenericParamFieldRefGenericDOMapper {


    @Select(GenericParamTypeRefGenericSQLConstants.QUERY_BY_PARAM_AND_GENERIC_TYPE_ID)
    GenericParamFieldRefGenericDO queryByParamAndGenericTypeId(@Param("genericParamFieldId") Integer genericParamFieldId, @Param("genericTypeId") Integer genericTypeId);

    @Insert(GenericParamTypeRefGenericSQLConstants.PERSIST)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void persist(GenericParamFieldRefGenericDO paramTypeRefGenericDO);

    @Delete(GenericParamTypeRefGenericSQLConstants.DELETE_BY_ID)
    Integer deleteById(@Param("id") Integer id);

    @Select(GenericParamTypeRefGenericSQLConstants.QUERY_BY_FIELD_ID)
    List<GenericParamFieldRefGenericDO> queryByFiledId(@Param("genericParamFieldId") Integer fieldId);
}
