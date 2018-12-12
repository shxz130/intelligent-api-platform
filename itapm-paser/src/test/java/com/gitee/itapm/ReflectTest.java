package com.gitee.itapm;

import com.gitee.itapm.bean.TestBean;
import com.gitee.itapm.utils.reflect.ReflectionUtils;
import junit.framework.Test;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by jetty on 2018/12/11.
 */
public class ReflectTest {

    @org.junit.Test
    public void test(){

        List<Field> allFields=ReflectionUtils.findAllFields(TestBean.class);
        for(Field field: allFields) {
            System.out.println("name:" + field.getName());
            System.out.println("name:" + field.getGenericType());

            Type type = field.getGenericType();
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] types = parameterizedType.getActualTypeArguments();
                for (Type argument : types) {
                    if (argument instanceof Class) {
                        Class clazz = (Class) argument;
                        System.out.println(clazz.getCanonicalName());
                    }
                }
            }

            System.out.println(field.getClass().getComponentType());
        }
    }

    @org.junit.Test
    public void test2(){
        System.out.println(String.class.getCanonicalName());
    }


}
