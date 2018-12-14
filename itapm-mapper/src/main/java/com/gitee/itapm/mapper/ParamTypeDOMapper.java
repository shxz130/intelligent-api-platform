package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.ParamTypeDO;
import com.gitee.itapm.mapper.constants.ParamTypeSQLConstants;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.MappedJdbcTypes;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamTypeDOMapper {


    @Select(ParamTypeSQLConstants.QUERY_BY_INTERFACE_DETAIL_ID_NAME_AND_RESOURCE)
    public ParamTypeDO queryByInterfaceDetailIdNameAndResource(Integer interfaceDetailId, String name, String resource);

    @Insert(ParamTypeSQLConstants.PERSIST)
    @Options(useGeneratedKeys=true, keyProperty="id")
    public void persist(ParamTypeDO paramTypeDO);
}
