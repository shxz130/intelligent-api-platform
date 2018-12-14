package com.gitee.itapm.mapper.bean;

import com.gitee.itapm.mapper.bean.parent.AbstractDO;
import lombok.Data;

import java.util.Date;

/**
 * Created by jetty on 2018/12/12.
 */
@Data
public class SystemVersionDO extends AbstractDO {

    private Integer id;

    private Integer systemId;

    private String systemName;

    private String systemVersion;

    private Date createTime;

    private Date updateTime;

}
