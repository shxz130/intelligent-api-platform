package com.gitee.itapm.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jetty on 2018/12/11.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Catagory {
   String value() default "";
}
