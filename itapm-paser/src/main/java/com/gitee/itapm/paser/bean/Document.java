package com.gitee.itapm.paser.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by jetty on 2018/12/11.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    private String systemEnName;

    private String systemChName;

    private String version;

    private List<Catagory> catagoryList;



}
