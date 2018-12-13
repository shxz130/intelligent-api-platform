package com.gitee.itapm.service.bean;

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
public class ParamFieldBO {

    private Integer id;

    private Integer paramTypeId;

    private String paramName;

    private String paramType;

    private Integer paramLength;

    private String required;

    private String defaultValue;

    private String paramDescription;

    private String example;

    private Date createTime;

    private Date updateTime;



}
