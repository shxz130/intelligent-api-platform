package com.gitee.itapm.paser.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by jetty on 2018/12/12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {

    private String name;

    private List<ParamField> paramFieldList;


}
