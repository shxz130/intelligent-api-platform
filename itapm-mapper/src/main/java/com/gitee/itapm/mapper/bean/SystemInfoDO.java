package com.gitee.itapm.mapper.bean;

import com.gitee.itapm.mapper.bean.parent.AbstractDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by jetty on 2018/12/9.
 */
@ToString
@NoArgsConstructor
@Data
public class SystemInfoDO extends AbstractDO{

    private Integer id;

    private String chName;

    private String enName;

    private String description;

    private String status;

    private Date createTime;

    private Date updateTime;

}
