package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.ParamGenericTypeDO;
import com.gitee.itapm.mapper.constants.ParamGenericTypeSQLConstants;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamGenericTypeDOMapper {

    @Select(ParamGenericTypeSQLConstants.QUERY_BY_SYSTEM_VERSION_ID_AND_NAME)
    ParamGenericTypeDO queryBySystemVersionIdAndName(Integer typeId, String name);

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert(ParamGenericTypeSQLConstants.PERSIST)
    void persist(ParamGenericTypeDO paramGenericTypeDO);
}