package com.gitee.itapm.rest;

import com.gitee.itapm.bean.RestResp;
import com.gitee.itapm.bean.RestResult;
import com.gitee.itapm.paser.ParamParseEngine;
import com.gitee.itapm.paser.bean.Parameter;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jetty on 2018/12/22.
 */
public class Test {

    @org.junit.Test
    public void debugTest(){

        RestResp<RestResult> restResp=new RestResp<RestResult>();

        Type mySuperclass = restResp.getClass().getGenericSuperclass();

        Type[] gTypes = ((ParameterizedType)mySuperclass).getActualTypeArguments();
        for(Type gType : gTypes){
            System.out.println("Generic type:"+gType.toString());
        }
        ParamParseEngine.parse(new Class[]{restResp.getClass()},new HashMap<>());

   /*
        Method[] methods=RestController.class.getMethods();
        for(Method method: methods){
            List<Parameter> parameterList= ParamParseEngine.parse(method.getParameterTypes(), new HashMap<>());
            System.out.println(parameterList);
        }*/
    }

}
