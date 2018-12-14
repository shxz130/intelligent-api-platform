package com.gitee.itapm.mapper.bean;

import com.gitee.itapm.mapper.bean.parent.AbstractDO;
import lombok.Data;

import java.util.Date;

/**
 * Created by jetty on 2018/12/13.
 */
@Data
public class ParamTypeDO extends AbstractDO {

    public static final String RESOURCE_REQ="REQ";

    public static final String RESOURCE_RESP="RESP";

    public static final String RESOURCE_GENERIC="GENERIC";

    private Integer id;

    private Integer interfaceDetailId;

    private String paramTypeName;
    /*** 参数类型 REQ，RESP */
    private String resource;

    private Date createTime;

    private Date updateTime;


}
