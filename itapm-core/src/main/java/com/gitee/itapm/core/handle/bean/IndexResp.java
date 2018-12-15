package com.gitee.itapm.core.handle.bean;

import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.Data;

import java.util.List;

/**
 * Created by jetty on 2018/12/15.
 */
@Data
public class IndexResp extends BaseBO {

    private List<SystemCatagoryInterfaceDetail> systemCatagoryInterfaceDetailList;

}
