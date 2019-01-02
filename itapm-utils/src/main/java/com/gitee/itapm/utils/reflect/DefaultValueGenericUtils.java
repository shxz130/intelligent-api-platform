package com.gitee.itapm.utils.reflect;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by jetty on 2019/1/2.
 */
public class DefaultValueGenericUtils {


    private static final List<String> basicTypeList= Arrays.asList("int","byte","short","long","float","double","boolean","char");


    private static final List<String> javaTypeList=Arrays.asList("String","java.math.BigDecimal","Double","Long","Date","BigDecimal");
    /**
     * 根据基本类型获取默认值
     *
     */
    public static Object getDefaultValueByJavaType(String type){
        if(isBasicType(type)){
            return getDefaultValueByType(type);
        }
        if(isJavaType(type)){
            return getDefaultValueByUtilMathLongType(type);
        }
        return "";
    }

    /**
     * 是否是java类型
     *
     *
     * @param type
     * @return
     */
    private static boolean isJavaType(String type) {
        return javaTypeList.contains(type);
    }


    /**
     * 获取基本类型的默认值
     *
     *
     * @param type
     * @return
     */
    private static Object getDefaultValueByType(String type) {
        if("int".equals(type)){
            return 0;
        }
        if("char".equals(type)){
            return 'a';
        }
        if("short".equals(type)){
            return 1;
        }
        if("long".equals(type)){
            long a=4l;
            return a;
        }
        if("float".equals(type)){
            float a=1f;
            return a;
        }
        if("double".equals(type)){
            double a=1d;
        }
        if("boolean".equals(type)){
            return true;
        }
        if("char".equals(type)){
            return 'a';
        }
        return "";
    }


    /**
     * 是否是java的基本类型
     *
     * @param type
     * @return
     */
    private static boolean isBasicType(String type) {
       return basicTypeList.contains(type);
    }

    /**
     * 获取utils，math，long包下的类型的默认值
     *
     *
     * @param type
     * @return
     */
    private static Object getDefaultValueByUtilMathLongType(String type) {
        if(type.contains("BigDecimal")){
            return new BigDecimal("100000");
        }
        if("String".equals(type)){
            return "XXX";
        }
        if("Double".equals(type)){
            return new Double(1);
        }
        if("Long".equals(type)){
            return new Long(1);
        }
        if("Date".equals(type)){
            return new Date();
        }
        return "";
    }

}
