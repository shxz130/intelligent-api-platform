package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.InterfaceDetailDO;
import com.gitee.itapm.mapper.bean.ParamInfoDO;
import com.gitee.itapm.mapper.constants.InterfaceDetailSQLConstants;
import com.gitee.itapm.mapper.constants.ParamInfoSQLConstants;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jetty on 2018/12/9.
 */
public interface ParamInfoDOMapper {

    @Insert(ParamInfoSQLConstants.INSERT_SQL)
    public Integer insert(ParamInfoDO paramInfoDO);

    @Select(ParamInfoSQLConstants.QUERY_BY_INTERFACE_DETAIL_ID_AND_PARENT_ID_IS_NULL)
    public List<ParamInfoDO> queryByInterfaceDetailIdAndParentIdIsNull(@Param("interfaceDetailId")Integer interfaceDetailId);
}
