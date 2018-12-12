package com.gitee.itapm.service.bean;

import com.gitee.itapm.mapper.bean.parent.AbstractDO;
import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by jetty on 2018/12/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemInfoBO extends BaseBO {

    private Integer id;

    private String chName;

    private String enName;

    private String description;

    private String status;

    private Date createTime;

    private Date updateTime;
}
