package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.InterfaceDetailDO;
import com.gitee.itapm.mapper.constants.InterfaceDetailSQLConstants;
import com.gitee.itapm.mapper.constants.ParamInfoSQLConstants;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by jetty on 2018/12/9.
 */
public interface ParamInfoDOMapper {

    @Insert(ParamInfoSQLConstants.INSERT_SQL)
    public Integer insert(InterfaceDetailDO interfaceDetailDO);
}
