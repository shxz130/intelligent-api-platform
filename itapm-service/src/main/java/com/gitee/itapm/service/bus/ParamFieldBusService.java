package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.ParamFieldService;
import com.gitee.itapm.service.bean.ParamFieldBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class ParamFieldBusService {

    @Autowired
    private ParamFieldService paramFieldService;

    public synchronized ParamFieldBO persist(ParamFieldBO paramFieldBO){
        ParamFieldBO result= paramFieldService.queryByParamTypeIdAndParamName(paramFieldBO.getParamTypeId(), paramFieldBO.getParamName());
        if(result==null){
            result=paramFieldService.persist(paramFieldBO);
        }else{
            paramFieldBO.setId(result.getId());
            result=paramFieldBO;
            paramFieldService.updateById(paramFieldBO);
        }
        return result;
    }

    public List<ParamFieldBO> queryByParamTypeId(Integer paramTypeId){
        return paramFieldService.queryByParamTypeId(paramTypeId);
    }

    public void deleteById(Integer id){
        paramFieldService.deleteById(id);
    }
}
