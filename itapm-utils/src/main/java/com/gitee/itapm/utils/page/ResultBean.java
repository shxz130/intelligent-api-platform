package com.gitee.itapm.utils.page;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by jetty on 2018/12/9.
 */
@Data
@ToString
@NoArgsConstructor
public class ResultBean<T>{

    private T data;

    private String code;

    private String message;

    public ResultBean(T t){
        this.data=t;
    }
}
