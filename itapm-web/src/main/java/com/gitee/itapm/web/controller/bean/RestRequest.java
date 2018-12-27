package com.gitee.itapm.web.controller.bean;

import com.gitee.itapm.web.controller.bean.parent.BaseDTO;
import lombok.Data;

/**
 * Created by jetty on 2018/12/26.
 */
@Data
public class RestRequest extends BaseDTO {

    private String requestData;

    private String requestMethod;

    private String requestBaseUrl;

    private String requestRelativeUrl;

    private String cookies;

    private String headers;

}
