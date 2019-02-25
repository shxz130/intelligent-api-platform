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
    NOT_FOUND_SYSTEM("000001","未找到该系统"),
    PARAM_IS_NOT_RIGHT("000002","参数不正确"),
    FAIL("999999","未知错误"),
    ;
    private String code;

    private String message;

}
