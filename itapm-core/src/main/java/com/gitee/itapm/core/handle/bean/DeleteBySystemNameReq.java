package com.gitee.itapm.core.handle.bean;

import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jetty on 2019/2/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBySystemNameReq extends BaseBO {

    private String name;
}
