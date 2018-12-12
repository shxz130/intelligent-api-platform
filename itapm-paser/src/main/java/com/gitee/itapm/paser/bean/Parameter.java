package com.gitee.itapm.paser.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by jetty on 2018/12/12.
 */
@Data
@AllArgsConstructor
public class Parameter {

    private String name;

    private List<ParamField> paramFieldList;


}
