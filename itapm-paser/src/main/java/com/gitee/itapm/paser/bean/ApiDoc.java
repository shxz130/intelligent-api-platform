package com.gitee.itapm.paser.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by jetty on 2018/12/11.
 */
@NoArgsConstructor
@Data
public class ApiDoc {

    /**
     * 目录名称
     */
    private String catagoryName;

    /**
     * 协议类型 dubbo/rest
     */
    private String type;
    /**
     * 请求类型  rest时，区分get/post
     */
    private String requestType;
    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;
    /**
     * 地址 rest时，为url地址
     */
    private String address;

    /**
     * callers 调用方
     */
    private String callers;

    /**
     * 状态
     *
     */
    private String status;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 备注
     */
    private String memo;

    /**
     * 请求参数列表
     */
    private List<Parameter> reqParamList;

    /**
     * 返回参数列表
     */
    private List<Parameter> respParamList;


    /**
     * 返回值或者请求参数依赖的列表
     *
     */
    private List<Parameter> genericParameterList;

}
