package com.gitee.itapm.service.bus;

import com.gitee.itapm.mapper.bean.InterfaceDetailDO;
import com.gitee.itapm.service.InterfaceDetailService;
import com.gitee.itapm.service.bean.InterfaceDetailBO;
import com.gitee.itapm.service.bean.ParamFieldBO;
import com.gitee.itapm.service.bean.ParamTypeBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class InterfaceDetailBusService {

    @Autowired
    private InterfaceDetailService interfaceDetailService;
    @Autowired
    private ParamTypeBusService paramTypeBusService;

    public synchronized InterfaceDetailBO persist(InterfaceDetailBO interfaceDetailBO){
        InterfaceDetailBO result= interfaceDetailService.queryBySystemVersionIdAndName(interfaceDetailBO.getSystemVersionId(), interfaceDetailBO.getName());
        if(result==null){
            interfaceDetailService.persist(interfaceDetailBO);
            result=interfaceDetailBO;
        }else{
            interfaceDetailBO.setId(result.getId());
            interfaceDetailService.updateById(interfaceDetailBO);
            result= interfaceDetailBO;
        }
        return result;
    }

    public List<InterfaceDetailBO> queryByCatagoryId(Integer catagoryId){
        return interfaceDetailService.queryByCatagoryId(catagoryId);
    }

    public void deleteById(Integer id){
        interfaceDetailService.deleteById(id);
        InterfaceDetailBO interfaceDetailBO=interfaceDetailService.queryById(id);
        ParamTypeBO req=paramTypeBusService.queryByInterfaceDetailIdAndResource(id, "REQ");
        ParamTypeBO resp=paramTypeBusService.queryByInterfaceDetailIdAndResource(id, "RESP");
        if(req!=null){
            paramTypeBusService.deleteById(req.getId());
        }
        if(resp!=null){
            paramTypeBusService.deleteById(resp.getId());
        }
    }

    public InterfaceDetailBO queryById(Integer interfaceDetailId) {
        return interfaceDetailService.queryById(interfaceDetailId);
    }


    public List<InterfaceDetailBO> queryBySystemVersionId(Integer systemVersionId) {
        return interfaceDetailService.queryBySystemVersionId(systemVersionId);
    }

    public List<InterfaceDetailBO> queryBySystemVersionIdAndCondition(Integer systemVersionId, String searchKey) {
        return interfaceDetailService.queryBySystemVersionIdAndCondition(systemVersionId,searchKey);
    }
}
