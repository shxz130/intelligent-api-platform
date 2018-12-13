package com.gitee.itapm.service.bean;

import com.gitee.itapm.mapper.bean.parent.AbstractDO;
import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by jetty on 2018/12/9.
 */
@ToString
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id","createTime","updateTime"})
public class InterfaceCatagoryBO extends BaseBO {

    private Integer id;

    private Integer systemVersionId;

    private String name;

    private String status;

    private Date createTime;

    private Date updateTime;

}
