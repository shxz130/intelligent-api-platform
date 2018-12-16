package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.ParamGenericTypeDO;
import com.gitee.itapm.mapper.constants.ParamGenericTypeSQLConstants;
import org.apache.ibatis.annotations.*;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamGenericTypeDOMapper {

    @Select(ParamGenericTypeSQLConstants.QUERY_BY_SYSTEM_VERSION_ID_AND_NAME)
    ParamGenericTypeDO queryBySystemVersionIdAndName(@Param("systemVersionId")Integer typeId,@Param("name") String name);

    @Insert(ParamGenericTypeSQLConstants.PERSIST)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void persist(ParamGenericTypeDO paramGenericTypeDO);

    @Select(ParamGenericTypeSQLConstants.QUERY_BY_ID)
    ParamGenericTypeDO queryById(Integer genericTypeId);

    @Delete(ParamGenericTypeSQLConstants.DELETE_BY_ID)
    void deleteById(@Param("id")Integer genericTypeId);
}
