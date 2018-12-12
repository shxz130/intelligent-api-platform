package com.gitee.itapm.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by jetty on 2018/12/11.
 */

@Data
public class AbstractBean {

    private String name;

    private List<String> parentList;
}
