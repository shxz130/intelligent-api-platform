package com.gitee.itapm.paser;

import com.gitee.itapm.annotations.DubboApi;
import com.gitee.itapm.annotations.RestApi;
import com.gitee.itapm.annotations.enums.ProtocolType;
import com.gitee.itapm.paser.bean.ApiDoc;
import com.gitee.itapm.paser.bean.Catagory;
import com.gitee.itapm.paser.bean.ParamField;
import com.gitee.itapm.paser.bean.Parameter;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by jetty on 2018/12/11.
 */
@Slf4j
public class ClassParseEngine {

    private static final String REST_URL_KEY="restUrls";
    private static final String REST_METHOD_KEY="restMethods";
    public static List<Catagory> parse(List<Class> clazzList){
        List<Catagory> catagoryList = new ArrayList<>();


        List<Method> dubboApiMethodList=getAllDubboApiMethods(clazzList);
        List<ApiDoc> dubboApiDocList=parseMethodList2DubboApiDocList(dubboApiMethodList);


        List<Method> restApiMethodList=getAllRestApiMethods(clazzList);
        List<ApiDoc> restApiDocList=parseMethodList2RestApiDocList(restApiMethodList);


        catagoryList.addAll(convertApiDosList2Catagory(dubboApiDocList));
        catagoryList.addAll(convertApiDosList2Catagory(restApiDocList));

        return catagoryList;
    }


    private static List<Catagory> convertApiDosList2Catagory( List<ApiDoc> apiDocList){
        List<String> catagoryNameList=new ArrayList<>();
        Map<String,Catagory> catagoryMap=new HashMap<>();
        for(ApiDoc apiDoc: apiDocList){
            Catagory catagory=catagoryMap.get(apiDoc.getCatagoryName());
            if(catagory!=null){
                catagoryMap.get(apiDoc.getCatagoryName()).getApiDocList().add(apiDoc);
            }else{
                catagoryMap.put(apiDoc.getCatagoryName(),new Catagory(apiDoc.getCatagoryName()));
                catagoryMap.get(apiDoc.getCatagoryName()).getApiDocList().add(apiDoc);
            }
        }
        return new ArrayList<Catagory>(catagoryMap.values());

    }



    private static List<ApiDoc> parseMethodList2DubboApiDocList(List<Method> methodList){
        List<ApiDoc> apiDocList=new ArrayList<>();
        for(Method method:methodList){
            apiDocList.add(parseMethod2DubboApiDoc(method));
        }
        return apiDocList;
    }


    private static List<ApiDoc> parseMethodList2RestApiDocList(List<Method> methodList){
        List<ApiDoc> apiDocList=new ArrayList<>();
        for(Method method:methodList){
            apiDocList.add(parseMethod2RestApiDoc(method));
        }
        return apiDocList;
    }



    private static ApiDoc parseMethod2DubboApiDoc(Method method){
        ApiDoc apiDoc=new ApiDoc();
        DubboApi dubboApi=method.getAnnotation(DubboApi.class);
        apiDoc.setCatagoryName(getCatagoryNameByMethod(method));
        apiDoc.setClassName(method.getDeclaringClass().getCanonicalName());
        apiDoc.setAddress(null);
        apiDoc.setDescription(dubboApi.description());
        apiDoc.setMemo(dubboApi.memo());
        apiDoc.setMethodName(method.getName());
        apiDoc.setRequestType(null);
        apiDoc.setStatus(dubboApi.status().toString());
        apiDoc.setType(ProtocolType.DUBBO.toString());
        apiDoc.setCallers(Arrays.asList(dubboApi.caller()));
        Map contextMap=new HashMap<String,Class>();
        //此map用来缓存泛型中的class，反射会加载出错。
        List<Parameter> reqParameteList=ParamParseEngine.parse(method.getParameterTypes(),contextMap);
        apiDoc.setReqParamList(reqParameteList);
        List<Parameter> respParamterList=ParamParseEngine.parse(new Class[]{method.getReturnType()},contextMap);
        apiDoc.setRespParamList(respParamterList);

        Map genericContextMap=new HashMap<String,Class>();
        List<Parameter> genericParameterList=getGenericParameterList(contextMap,genericContextMap);
        apiDoc.setGenericParameterList(genericParameterList);

        //泛型或者自定义类关联的参数列表
        List<Parameter> genericRefParameterList=getGenericParameterList(genericContextMap,new HashMap<>());
        apiDoc.setGenericRefGenericParameterList(genericRefParameterList);
        return apiDoc;
    }



    private static ApiDoc parseMethod2RestApiDoc(Method method){
        Map<String,String> restData=getRestAddressAndMethod(method);
        ApiDoc apiDoc=new ApiDoc();
        RestApi restApi=method.getAnnotation(RestApi.class);
        apiDoc.setCatagoryName(getCatagoryNameByMethod(method));
        apiDoc.setClassName(method.getDeclaringClass().getCanonicalName());
        apiDoc.setAddress(restData.get(REST_URL_KEY));
        apiDoc.setDescription(restApi.description());
        apiDoc.setMemo(restApi.memo());
        apiDoc.setMethodName(method.getName());
        apiDoc.setRequestType(restData.get(REST_METHOD_KEY));
        apiDoc.setStatus(restApi.status().toString());
        apiDoc.setType(ProtocolType.REST.toString());
        apiDoc.setCallers(Arrays.asList(restApi.caller()));
        Map contextMap=new HashMap<String,Class>();
        //此map用来缓存泛型中的class，反射会加载出错。
        List<Parameter> reqParameteList=ParamParseEngine.parse(method.getParameterTypes(),contextMap);
        apiDoc.setReqParamList(reqParameteList);

        if(method.getGenericReturnType()instanceof ParameterizedType){
            List<Parameter> respParamterList=ParamParseEngine.parse((ParameterizedType)method.getGenericReturnType(),contextMap);
            apiDoc.setRespParamList(respParamterList);
        }else{
            List<Parameter> respParamterList=ParamParseEngine.parse(new Class[]{method.getReturnType()},contextMap);
            apiDoc.setRespParamList(respParamterList);

        }
        //泛型或者自定义类的参数
        Map genericContextMap=new HashMap<String,Class>();
        List<Parameter> genericParameters=getGenericParameterList(contextMap,genericContextMap);
        apiDoc.setGenericParameterList(genericParameters);

        //泛型或者自定义类关联的参数列表
        List<Parameter> genericRefParameterList=getGenericParameterList(genericContextMap,new HashMap<>());
        apiDoc.setGenericRefGenericParameterList(genericRefParameterList);
        return apiDoc;
    }


    private static Map<String,String> getRestAddressAndMethod(Method method){

        Map<String,String> restData=new HashMap<>();
        if(method.getAnnotation(RequestMapping.class)!=null){
            RequestMapping requestMapping=  method.getAnnotation(RequestMapping.class);
            restData.put(REST_URL_KEY,getUrl(requestMapping));
            restData.put(REST_METHOD_KEY,getMethod(requestMapping));
            return restData;
        }
        if(method.getAnnotation(GetMapping.class)!=null){
            GetMapping getMapping=method.getAnnotation(GetMapping.class);
            restData.put(REST_URL_KEY, getUrl(getMapping));
            restData.put(REST_METHOD_KEY, "GET");
            return restData;
        }
        if(method.getAnnotation(PostMapping.class)!=null){
            PostMapping postMapping=method.getAnnotation(PostMapping.class);
            restData.put(REST_URL_KEY, getUrl(postMapping));
            restData.put(REST_METHOD_KEY, "POST");
            return restData;
        }
        return restData;
    }

    private static String getUrl(RequestMapping requestMapping){
        String[] urls=requestMapping.value();
        if(urls!=null){
            return Joiner.on(";").join(urls);
        }
        return "";
    }



    private static String getUrl(GetMapping getMapping){
        String[] urls=getMapping.value();
        if(urls!=null){
            return Joiner.on(";").join(urls);
        }
        return "";
    }


    private static String getUrl(PostMapping postMapping){
        String[] urls=postMapping.value();
        if(urls!=null){
            return Joiner.on(";").join(urls);
        }
        return "";
    }


    private static String getMethod(RequestMapping requestMapping){
        RequestMethod[] requestMethods=requestMapping.method();
        if(requestMethods!=null){
            return Joiner.on(";").join(requestMethods);
        }
        return "";
    }





    private static List<Parameter> getGenericParameterList(Map<String,Class> contextMap,Map<String,Class> genericContextMap){

        List<Class> genericClassList=new ArrayList<>();
        for(Map.Entry<String,Class> entry:contextMap.entrySet()){
            genericClassList.add(entry.getValue());

        }
        Class[] genericClasses=new Class[contextMap.entrySet().size()];
        for(int i=0;i<genericClassList.size();i++){
            genericClasses[i]=genericClassList.get(i);
        }
        return ParamParseEngine.parse(genericClasses,genericContextMap);
    }


    private static String getCatagoryNameByMethod(Method method){
        com.gitee.itapm.annotations.Catagory catagory=method.getAnnotation(com.gitee.itapm.annotations.Catagory.class);
        if(catagory==null){
            catagory =method.getDeclaringClass().getAnnotation(com.gitee.itapm.annotations.Catagory.class);
        }
        if(catagory==null){
            return null;
        }else{
            return catagory.value();
        }
    }


    private static List<Method> getAllDubboApiMethods(List<Class> clazzList) {
        List<Method> methodList = new ArrayList<Method>();
        for (Class clazz : clazzList) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(DubboApi.class)) {
                    methodList.add(method);
                }
            }
        }
        return methodList;
    }


    private static List<Method> getAllRestApiMethods(List<Class> clazzList) {
        List<Method> methodList = new ArrayList<Method>();
        for (Class clazz : clazzList) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(RestApi.class)) {
                    methodList.add(method);
                }
            }
        }
        return methodList;
    }
}
