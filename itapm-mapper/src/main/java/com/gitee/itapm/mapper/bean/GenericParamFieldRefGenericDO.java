package com.gitee.itapm.mapper.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jetty on 2018/12/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericParamFieldRefGenericDO {

    private Integer id;

    private Integer genericParamFieldId;

    private Integer genericTypeId;

    public GenericParamFieldRefGenericDO(Integer genericParamFieldId, Integer genericTypeId){
        this.genericParamFieldId=this.genericParamFieldId;
        this.genericTypeId=this.genericTypeId;

    }
}
