package com.gitee.itapm.mapper.bean;

import com.gitee.itapm.mapper.bean.parent.AbstractDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jetty on 2018/12/13.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamFieldRefGenericDO extends AbstractDO {

    private Integer id;

    private Integer paramFieldId;

    private Integer genericTypeId;

    public ParamFieldRefGenericDO(Integer paramFieldId, Integer genericTypeId){
        this.paramFieldId=this.paramFieldId;
        this.genericTypeId=this.genericTypeId;

    }

}
