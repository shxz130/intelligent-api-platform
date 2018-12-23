package com.gitee.itapm.service.bean;

import com.gitee.itapm.service.bean.parent.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by jetty on 2018/12/13.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class GenericParamFieldRefGenericBO extends BaseBO {

    private Integer id;

    private Integer genericParamFieldId;

    private Integer genericTypeId;

    public GenericParamFieldRefGenericBO(Integer genericParamFieldId, Integer genericTypeId){
        this.genericParamFieldId=this.genericParamFieldId;
        this.genericTypeId=this.genericTypeId;
    }

}
