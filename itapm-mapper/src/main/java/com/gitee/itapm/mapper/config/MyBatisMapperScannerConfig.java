package com.gitee.itapm.mapper.config;


import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

/**
 * Created by jetty on 2018/12/9.
 */

@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        String basePackagePath= ClassUtils.getPackageName(this.getClass());
        mapperScannerConfigurer.setBasePackage(basePackagePath.substring(0, basePackagePath.lastIndexOf(".")));
        return mapperScannerConfigurer;
    }
}
