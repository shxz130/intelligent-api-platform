package com.gitee.itapm.core.handle.bean;

import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by jetty on 2018/12/9.
 */
@NoArgsConstructor
@ToString
@Data
public class SearchReq extends BaseBO{

    private Integer systemId;

    private String searchKey;

    private Integer currentPage=1;

    private Integer pageSize=10;

}
