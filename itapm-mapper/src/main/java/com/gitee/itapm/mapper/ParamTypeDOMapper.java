package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.ParamTypeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.MappedJdbcTypes;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamTypeDOMapper {

    public ParamTypeDO queryByInterfaceDetailIdNameAndResource(Integer interfaceDetailId, String name, String resource);

    public void persist(ParamTypeDO paramTypeDO);
}
