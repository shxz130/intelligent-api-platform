package com.gitee.itapm.utils.bean;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.beans.BeanCopier;



public final class BeanCopierUtils {

    private BeanCopierUtils() {}

    /** BeanCopier实例们的缓存 **/
    public static final Map<String, BeanCopier> BEAN_COPIER_MAP = new HashMap<String, BeanCopier>();

    /**
     * copy properties
     *
     * @param source
     * @param target
     */
    private static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = BEAN_COPIER_MAP.get(beanKey);
        if (copier == null) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIER_MAP.put(beanKey, copier);
        }
        copier.copy(source, target, null);
    }


    private static String generateKey(Class<?> class1, Class<?> class2) {
        return new StringBuilder().append(class1.toString()).append(class2.toString()).toString();
    }


    public static <T> T copyOne2One(Object source, Class<T> target)  {
        try{
            T instance = null;
            instance = target.newInstance();
            copyProperties(source, instance);
            return instance;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }


    public static <T> List<T> copyList2List(List<?> source, Class<T> target)
            throws InstantiationException, IllegalAccessException {
        if (source.isEmpty()) {
            return new ArrayList<T>();
        }
        List<T> result = new ArrayList<T>();
        for (Object obj : source) {
            result.add(copyOne2One(obj, target));
        }
        return result;
    }



}
