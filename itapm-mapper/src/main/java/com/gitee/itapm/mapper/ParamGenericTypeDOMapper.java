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

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert(ParamGenericTypeSQLConstants.PERSIST)
    void persist(ParamGenericTypeDO paramGenericTypeDO);
}
