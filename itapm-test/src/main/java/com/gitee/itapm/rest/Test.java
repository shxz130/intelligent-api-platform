package com.gitee.itapm.rest;

import com.alibaba.fastjson.JSON;
import com.gitee.itapm.bean.RestResp;
import com.gitee.itapm.bean.RestResult;
import com.gitee.itapm.paser.ParamParseEngine;
import com.gitee.itapm.paser.bean.Parameter;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

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



        for(Method method: RestController.class.getMethods()){

            List<Parameter> respParamterList=null;
            if(method.getGenericReturnType()instanceof ParameterizedType){
                respParamterList=ParamParseEngine.parse((ParameterizedType)method.getGenericReturnType(),new HashMap<String, Class>());
            }else{
                respParamterList=ParamParseEngine.parse(new Class[]{method.getReturnType()},new HashMap<String, Class>());

            }
            System.out.println(JSON.toJSONString(respParamterList));
        }

   /*
        Method[] methods=RestController.class.getMethods();
        for(Method method: methods){
            List<Parameter> parameterList= ParamParseEngine.parse(method.getParameterTypes(), new HashMap<>());
            System.out.println(parameterList);
        }*/
    }

}
