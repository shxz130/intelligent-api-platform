package com.gitee.itapm.web.controller.bean;

import com.gitee.itapm.web.controller.bean.parent.BaseDTO;
import lombok.Data;

/**
 * Created by jetty on 2018/12/26.
 */
@Data
public class DubboRequest extends BaseDTO {

    private String requestData;

    private String interfaceName;

    private String version;

    private String group;

    private String zookeeper;

    private String requestParamType;
}
