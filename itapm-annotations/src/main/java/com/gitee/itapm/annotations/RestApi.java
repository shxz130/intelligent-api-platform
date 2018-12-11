package com.gitee.itapm.annotations;

import com.gitee.itapm.annotations.enums.InterfaceStatus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jetty on 2018/12/11.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RestApi {

    String name() default "";

    InterfaceStatus status() default InterfaceStatus.ONLINE;

    String owner() default "";

    String[] caller() default {};

    String description() default "";

}
