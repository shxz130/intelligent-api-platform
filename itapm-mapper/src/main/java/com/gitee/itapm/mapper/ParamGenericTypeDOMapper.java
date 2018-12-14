package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.ParamGenericTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamGenericTypeDOMapper {

    ParamGenericTypeDO queryBySystemVersionIdAndName(Integer typeId, String name);

    void persist(ParamGenericTypeDO paramGenericTypeDO);
}
