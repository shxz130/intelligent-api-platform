package com.gitee.itapm.mapper.bean;

import com.gitee.itapm.mapper.bean.parent.AbstractDO;
import lombok.Data;

/**
 * Created by jetty on 2018/12/12.
 */
@Data
public class SystemVersionDO extends AbstractDO {

    private Integer id;

    private String systemId;

    private String systemName;

    private String systemVersion;

}
