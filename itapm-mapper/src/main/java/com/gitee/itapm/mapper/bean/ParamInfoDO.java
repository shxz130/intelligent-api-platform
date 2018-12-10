package com.gitee.itapm.mapper.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by jetty on 2018/12/9.
 */
@Data
@ToString
@NoArgsConstructor
public class ParamInfoDO {

    private Integer id;

    private String type;

    private Integer parentId;

    private Integer interfaceDetailId;

    private String paramName;

    private String paramType;

    private String paramLength;

    private String required;

    private String defaultValue;

    private String paramDescription;

    private String example;

    private Date createTime;

    private Date updateTime;



}
