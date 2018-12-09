package com.gitee.itapm.web.controller.restful;

import com.gitee.itapm.utils.page.ResultBean;
import com.gitee.itapm.web.utils.UrlPathConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jetty on 2018/12/9.
 */

@RestController
public class SystemRestController {

    @GetMapping(UrlPathConstants.ITAPM_QUERY_SYSTEM_LIST)
    public ResultBean<Integer> ruleInfoList(){
        return new ResultBean<>(1);
    }


}
