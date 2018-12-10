package com.gitee.itapm.mapper.bean;

import com.gitee.itapm.mapper.bean.parent.AbstractDO;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by jetty on 2018/12/9.
 */

@Data
@ToString
public class InterfaceDetailDO extends AbstractDO{

    private Integer id;

    private Integer systemId;

    private String systemName;

    private Integer catagoryId;

    private String name;

    private String address;

    private String users;

    private String status;

    private String description;

    private Date createTime;

    private Date updateTime;



}
