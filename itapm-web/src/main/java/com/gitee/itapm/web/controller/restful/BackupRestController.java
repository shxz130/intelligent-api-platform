package com.gitee.itapm.web.controller.restful;

import com.gitee.itapm.core.handle.backup.DeleteBySystemHandler;
import com.gitee.itapm.core.handle.bean.DeleteBySystemNameReq;
import com.gitee.itapm.utils.page.ResultBean;
import com.gitee.itapm.web.utils.UrlPathConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jetty on 2019/2/25.
 */
@RestController
public class BackupRestController {

    @Autowired
    private DeleteBySystemHandler deleteBySystemHandler;

    @PostMapping(UrlPathConstants.ITAPM_DELETE_SYSTEM)
    public ResultBean deleteSystem(@RequestBody String name){
        deleteBySystemHandler.handle(new DeleteBySystemNameReq(name.replace("name=","")),null);
        return new ResultBean();
    }


}
