package com.gitee.itapm.service.bean;

import com.alibaba.druid.util.StringUtils;
import com.gitee.itapm.mapper.bean.parent.AbstractDO;
import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Created by jetty on 2018/12/9.
 */

@Data
@ToString
@EqualsAndHashCode(of = {"id"})
public class InterfaceDetailBO extends BaseBO{

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

    private ParamTypeBO reqParamTypeBO;

    private ParamTypeBO respParamTypeBO;


}
