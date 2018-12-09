package com.gitee.itapm.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jetty on 2018/12/9.
 */
@Getter
@AllArgsConstructor
public enum RespCodeEnum {

    SUCCESS("000000","返回成功"),
    FAIL("999999","未知错误"),
    ;
    private String code;

    private String message;

}
