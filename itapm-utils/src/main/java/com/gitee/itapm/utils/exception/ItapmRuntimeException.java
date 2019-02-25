package com.gitee.itapm.utils.exception;

import com.gitee.itapm.utils.enums.RespCodeEnum;
import lombok.Getter;

/**
 * Created by jetty on 2018/12/9.
 */
@Getter
public class ItapmRuntimeException extends RuntimeException{

    private String code;

    private String message;


    public ItapmRuntimeException(RespCodeEnum respCodeEnum) {
        this.code=respCodeEnum.getCode();
        this.message=respCodeEnum.getMessage();
    }

    public ItapmRuntimeException(RespCodeEnum respCodeEnum, Throwable cause) {
        super(String.format("%s:%s",respCodeEnum.getCode(),respCodeEnum.getMessage()), cause);
        this.code=respCodeEnum.getCode();
        this.message=respCodeEnum.getMessage();
    }


}
