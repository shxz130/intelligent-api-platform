package com.gitee.itapm.service.bean;

import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.Data;

/**
 * Created by jetty on 2018/12/13.
 */
@Data
public class ParamGenericTypeBO extends BaseBO{

    private Integer id;

    private Integer systemVersionId;

    private String name;
}
