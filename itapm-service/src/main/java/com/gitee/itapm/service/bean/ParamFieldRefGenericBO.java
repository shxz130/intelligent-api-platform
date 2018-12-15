package com.gitee.itapm.service.bean;

import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jetty on 2018/12/13.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamFieldRefGenericBO extends BaseBO {

    private Integer id;

    private Integer paramFieldId;

    private Integer genericTypeId;

    public ParamFieldRefGenericBO(Integer paramFieldId, Integer genericTypeId){
        this.paramFieldId=this.paramFieldId;
        this.genericTypeId=this.genericTypeId;
    }

}
