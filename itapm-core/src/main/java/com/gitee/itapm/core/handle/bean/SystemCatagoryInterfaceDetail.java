package com.gitee.itapm.core.handle.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by jetty on 2018/12/15.
 */
@Data
public class SystemCatagoryInterfaceDetail {

    private Integer id;

    private String systemEnName;

    private String systemChName;

    private List<Catagory> catagoryList;

}
