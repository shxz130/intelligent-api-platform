package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.InterfaceCatagoryDO;
import com.gitee.itapm.mapper.bean.ParamTypeRefGenericDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamTypeRefGenericDOMapper {

    ParamTypeRefGenericDO queryByParamAndGenericTypeId(Integer paramTypeId, Integer genericTypeId);

    void persist(ParamTypeRefGenericDO paramTypeRefGenericDO);

    Integer deleteById(Integer id);

}
