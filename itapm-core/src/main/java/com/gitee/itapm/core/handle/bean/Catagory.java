package com.gitee.itapm.core.handle.bean;

import com.gitee.itapm.service.bean.InterfaceDetailBO;
import lombok.Data;

import java.util.List;

/**
 * Created by jetty on 2018/12/15.
 */
@Data
public class Catagory {

    private Integer id;

    private String catagoryName;

    private List<InterfaceDetailBO> interfaceDetailBOList;
}
