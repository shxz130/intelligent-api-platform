package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.InterfaceDetailService;
import com.gitee.itapm.service.bean.InterfaceDetailBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class InterfaceDetailBusService {

    @Autowired
    private InterfaceDetailService interfaceDetailService;


    public synchronized InterfaceDetailBO persist(InterfaceDetailBO interfaceDetailBO){
        InterfaceDetailBO result= interfaceDetailService.queryBySystemVersionIdAndName(interfaceDetailBO.getSystemVersionId(), interfaceDetailBO.getName());
        if(result==null){
            result= interfaceDetailService.persist(interfaceDetailBO);
        }else if(result.equals(interfaceDetailBO)){
            return result;
        }else{
            interfaceDetailBO.setId(result.getId());
            result=interfaceDetailBO;
            interfaceDetailService.updateById(interfaceDetailBO);
        }
        return result;
    }

    public List<InterfaceDetailBO> queryByCatagoryId(Integer catagoryId){
        return interfaceDetailService.queryByCatagoryId(catagoryId);
    }

    public void deleteById(Integer id){
        interfaceDetailService.deleteById(id);
    }
}
