package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.ParamTypeService;
import com.gitee.itapm.service.bean.ParamTypeBO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jetty on 2018/12/13.
 */
public class ParamTypeBusService {

    @Autowired
    private ParamTypeService paramTypeService;

    public synchronized ParamTypeBO persist(Integer systemVersionId,String className,String resource){
        ParamTypeBO paramTypeBO=paramTypeService.queryByInterfaceDetailIdNameAndResource(systemVersionId, className,resource);
        if(paramTypeBO==null){
            paramTypeBO=paramTypeService.persist(systemVersionId, className,resource);
        }
        return paramTypeBO;
    }

}
