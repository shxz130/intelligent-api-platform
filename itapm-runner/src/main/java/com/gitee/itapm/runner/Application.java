package com.gitee.itapm.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by jetty on 2018/12/9.
 */
@SpringBootApplication
@ComponentScan("com.gitee.itapm")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

}
