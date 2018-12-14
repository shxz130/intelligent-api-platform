package com.gitee.itapm.mapper.bean;

import com.gitee.itapm.mapper.bean.parent.AbstractDO;
import lombok.Data;

/**
 * Created by jetty on 2018/12/13.
 */
@Data
public class ParamGenericTypeDO extends AbstractDO{

    private Integer id;

    private Integer systemVersionId;

    private String name;
}
