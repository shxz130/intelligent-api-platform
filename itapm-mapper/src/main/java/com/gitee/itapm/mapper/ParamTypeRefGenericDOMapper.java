package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.InterfaceCatagoryDO;
import com.gitee.itapm.mapper.bean.ParamTypeRefGenericDO;
import com.gitee.itapm.mapper.constants.ParamTypeRefGenericSQLConstants;
import lombok.experimental.Delegate;
import org.apache.ibatis.annotations.*;

/**
 * Created by jetty on 2018/12/14.
 */
@Mapper
public interface ParamTypeRefGenericDOMapper {


    @Select(ParamTypeRefGenericSQLConstants.QUERY_BY_PARAM_AND_GENERIC_TYPE_ID)
    ParamTypeRefGenericDO queryByParamAndGenericTypeId(Integer paramTypeId, Integer genericTypeId);

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert(ParamTypeRefGenericSQLConstants.PERSIST)
    void persist(ParamTypeRefGenericDO paramTypeRefGenericDO);

    @Delete(ParamTypeRefGenericSQLConstants.DELETE_BY_ID)
    Integer deleteById(Integer id);

}
