package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.GenericParamFieldService;
import com.gitee.itapm.service.ParamFieldService;
import com.gitee.itapm.service.bean.GenericParamFieldBO;
import com.gitee.itapm.service.bean.ParamFieldBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class GenericParamFieldBusService  {

    @Autowired
    private GenericParamFieldService genericParamFieldService;

    public synchronized GenericParamFieldBO persist(GenericParamFieldBO paramFieldBO){
        GenericParamFieldBO result= genericParamFieldService.queryByParamTypeIdAndParamName(paramFieldBO.getGenericParamTypeId(), paramFieldBO.getParamName());
        if(result==null){
            genericParamFieldService.persist(paramFieldBO);
            result=paramFieldBO;
        }else{
            paramFieldBO.setId(result.getId());
            result=paramFieldBO;
            genericParamFieldService.updateById(paramFieldBO);
        }
        return result;
    }

    public List<GenericParamFieldBO> queryByParamTypeId(Integer paramTypeId){
        return genericParamFieldService.queryByParamTypeId(paramTypeId);
    }

    public void deleteById(Integer id){
        genericParamFieldService.deleteById(id);
    }


}
