package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.ParamFieldDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamFieldDOMapper {


    public ParamFieldDO queryByParamTypeIdAndParamName(Integer paramTypeId, String name);

    List<ParamFieldDO> queryByParamTypeId(Integer paramTypeId);

    Integer deleteById(Integer id);

    void persist(ParamFieldDO paramFieldDO);

    Integer updateById(ParamFieldDO paramFieldDO);
}
