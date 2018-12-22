package com.gitee.itapm.annotations;

import com.gitee.itapm.annotations.enums.InterfaceStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jetty on 2018/12/11.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RestApi {

    String description() default "";

    InterfaceStatus status() default InterfaceStatus.ONLINE;

    String owner() default "";

    String[] caller() default {};

    String memo() default "";


}
