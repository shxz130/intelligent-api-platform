package com.gitee.itapm.service.bean;

import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.Data;

/**
 * Created by jetty on 2018/12/12.
 */
@Data
public class SystemVersionBO extends BaseBO {

    private Integer id;

    private String systemId;

    private String systemName;

    private String systemVersion;

}
