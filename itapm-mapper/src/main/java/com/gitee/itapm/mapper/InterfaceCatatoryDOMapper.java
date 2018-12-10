package com.gitee.itapm.mapper;

import com.gitee.itapm.mapper.bean.InterfaceCatagoryDO;
import com.gitee.itapm.mapper.constants.InterfaceCatagorySQLConstants;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by jetty on 2018/12/9.
 */
public interface InterfaceCatatoryDOMapper {


    @Insert(InterfaceCatagorySQLConstants.INSERT_SQL)
    public Integer insert(InterfaceCatagoryDO interfaceCatagoryDO);

}
