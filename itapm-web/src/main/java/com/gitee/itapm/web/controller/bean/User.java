package com.gitee.itapm.web.controller.bean;

import com.gitee.itapm.web.controller.bean.parent.BaseDTO;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jetty on 2018/12/9.
 */
@Getter
@ToString(exclude = {"password"})
public class User extends BaseDTO {

    private String name;

    private String password;
}
