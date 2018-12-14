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

    private Integer systemVersionId;

    private String systemName;

    private Integer catagoryId;

    private String name;

    private String address;


    private String caller;

    /**
     * 协议类型 dubbo/rest
     */
    private String type;
    /**
     * 请求类型  rest时，区分get/post
     */
    private String requestType;

    private String status;

    private String description;

    private String memo;

    private Date createTime;

    private Date updateTime;



}
