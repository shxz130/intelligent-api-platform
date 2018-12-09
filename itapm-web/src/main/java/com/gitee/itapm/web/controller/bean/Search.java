package com.gitee.itapm.web.controller.bean;

import com.gitee.itapm.web.controller.bean.parent.BaseDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by jetty on 2018/12/9.
 */
@NoArgsConstructor
@ToString
@Data
public class Search extends BaseDTO {

    private String systemId;

    private String searchKey;

    private Integer currentPage;

    private Integer pageSize;

}
