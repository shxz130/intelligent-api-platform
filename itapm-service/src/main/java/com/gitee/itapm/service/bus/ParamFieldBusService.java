package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.ParamFieldService;
import com.gitee.itapm.service.bean.ParamFieldBO;
import com.gitee.itapm.service.bean.ParamFieldRefGenericBO;
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
    @Autowired
    private ParamTypeRefGenericBusService  paramTypeRefGenericBusService;
    @Autowired
    private ParamGenericTypeBusService paramGenericTypeBusService;

    public synchronized ParamFieldBO persist(ParamFieldBO paramFieldBO){
        ParamFieldBO result= paramFieldService.queryByParamTypeIdAndParamName(paramFieldBO.getParamTypeId(), paramFieldBO.getParamName());
        if(result==null){
            paramFieldService.persist(paramFieldBO);
            result=paramFieldBO;
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
        List<ParamFieldRefGenericBO> paramFieldRefGenericList=paramTypeRefGenericBusService.queryByFieldId(id);
        for(ParamFieldRefGenericBO paramFieldRefGenericBO: paramFieldRefGenericList){
            paramTypeRefGenericBusService.deleteById(paramFieldRefGenericBO.getId());
            paramGenericTypeBusService.deleteById(paramFieldRefGenericBO.getGenericTypeId());
        }

    }
}
