package com.gitee.itapm.service.bus;

import com.gitee.itapm.service.GenericParamTypeRefGenericService;
import com.gitee.itapm.service.ParamTypeRefGenericService;
import com.gitee.itapm.service.bean.GenericParamFieldRefGenericBO;
import com.gitee.itapm.service.bean.ParamFieldRefGenericBO;
import com.gitee.itapm.utils.bean.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by jetty on 2018/12/13.
 */
@Component
public class GenericParamTypeRefGenericBusService {


    @Autowired
    private GenericParamTypeRefGenericService genericParamTypeRefGenericService;


    public synchronized GenericParamFieldRefGenericBO persist(Integer paramFiledId,Integer genericTypeId){
        GenericParamFieldRefGenericBO paramGenericTypeBO = genericParamTypeRefGenericService.queryByParamAndGenericTypeId(paramFiledId, genericTypeId);
        if(paramGenericTypeBO==null){
            paramGenericTypeBO =genericParamTypeRefGenericService.persist(paramFiledId, genericTypeId);
        }
        return paramGenericTypeBO;
    }

    public List<GenericParamFieldRefGenericBO> queryByFieldId(Integer fieldId) {
        List<GenericParamFieldRefGenericBO> paramTypeRefGenericList= genericParamTypeRefGenericService.queryByFieldId(fieldId);
        if(CollectionUtils.isEmpty(paramTypeRefGenericList)){
            return Collections.emptyList();
        }
        return BeanCopierUtils.copyList2List(paramTypeRefGenericList,GenericParamFieldRefGenericBO.class);
    }

    public void deleteById(Integer id) {
        genericParamTypeRefGenericService.deleteById(id);
    }
}
