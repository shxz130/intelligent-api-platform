package com.gitee.itapm.service.bean;

import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Data
public class ParamTypeBO extends BaseBO {

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

    private List<ParamFieldBO> paramFieldList;

    private String jsonFormat;

}
