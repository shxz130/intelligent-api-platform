package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.ParamTypeService;
import com.gitee.itapm.service.bean.ParamFieldBO;
import com.gitee.itapm.service.bean.ParamTypeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class ParamTypeBusService {

    @Autowired
    private ParamTypeService paramTypeService;
    @Autowired
    private ParamFieldBusService paramFieldBusService;


    public synchronized ParamTypeBO persist(Integer systemVersionId,String className,String resource){
        ParamTypeBO paramTypeBO=paramTypeService.queryByInterfaceDetailIdResource(systemVersionId, resource);
        if(paramTypeBO==null){
            paramTypeBO=paramTypeService.persist(systemVersionId, className,resource);
        }
        return paramTypeBO;
    }

    public ParamTypeBO queryByInterfaceDetailIdAndResource(Integer interfaceDetailId, String resource) {
        return paramTypeService.queryByInterfaceDetailIdResource(interfaceDetailId, resource);
    }

    public void deleteById(Integer id) {
        paramTypeService.deleteById(id);
        List<ParamFieldBO> paramFieldBOList= paramFieldBusService.queryByParamTypeId(id);
        for(ParamFieldBO paramFieldBO: paramFieldBOList){
            paramFieldBusService.deleteById(paramFieldBO.getId());
        }
    }
}
