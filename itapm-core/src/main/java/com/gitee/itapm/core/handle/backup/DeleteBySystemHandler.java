package com.gitee.itapm.core.handle.backup;

import com.gitee.itapm.core.handle.bean.DeleteBySystemNameReq;
import com.gitee.itapm.core.handle.parent.AbstractHandler;
import com.gitee.itapm.service.bean.InterfaceCatagoryBO;
import com.gitee.itapm.service.bean.SystemInfoBO;
import com.gitee.itapm.service.bean.SystemVersionBO;
import com.gitee.itapm.service.bean.parent.BaseBO;
import com.gitee.itapm.service.bus.*;
import com.gitee.itapm.utils.enums.RespCodeEnum;
import com.gitee.itapm.utils.exception.ItapmRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by jetty on 2019/2/25.
 */

@Component
@Slf4j
public class DeleteBySystemHandler extends AbstractHandler<DeleteBySystemNameReq,BaseBO> {

    @Autowired
    private SystemInfoBusService systemInfoBusService;
    @Autowired
    private InterfaceCatagoryBusService interfaceCatagoryBusService;
    @Autowired
    private SystemVersionBusService systemVersionBusService;


    @Override
    protected void check(DeleteBySystemNameReq request, BaseBO response, Map map) {
        super.check(request, response, map);
        if(StringUtils.isBlank(request.getName())){
            throw new ItapmRuntimeException(RespCodeEnum.PARAM_IS_NOT_RIGHT);
        }
    }

    @Override
    protected void doHandle(DeleteBySystemNameReq request, BaseBO response, Map map) {

        SystemInfoBO systemInfoBO=systemInfoBusService.queryListBySystemName(request.getName().toUpperCase());
        if(systemInfoBO==null){
            throw new ItapmRuntimeException(RespCodeEnum.NOT_FOUND_SYSTEM);
        }
        while(true){
            SystemVersionBO systemVersionBO=systemVersionBusService.queryLastOneBySystemInfoId(systemInfoBO.getId());
            if(systemVersionBO!=null){
                systemVersionBusService.deleteById(systemVersionBO.getId());
                List<InterfaceCatagoryBO> catagoryList=interfaceCatagoryBusService.queryBySystemVersionId(systemVersionBO.getId());
                //删除系统没有的分类
                for(InterfaceCatagoryBO interfaceCatagoryBO:catagoryList){
                    try{
                        interfaceCatagoryBusService.deleteById(interfaceCatagoryBO.getId());
                    }catch (Exception e){
                        log.error("删除数据出错",e);
                    }
                }
            }else{
                systemInfoBusService.deleteById(systemInfoBO.getId());
                return;
            }
        }



    }
}
