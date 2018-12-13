package com.gitee.itapm.paser;

import com.gitee.itapm.annotations.DubboApi;
import com.gitee.itapm.annotations.enums.ParamDirectoryType;
import com.gitee.itapm.annotations.enums.ProtocalType;
import com.gitee.itapm.paser.bean.ApiDoc;
import com.gitee.itapm.paser.bean.Catagory;
import com.gitee.itapm.paser.bean.ParamField;
import com.gitee.itapm.paser.bean.Parameter;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by jetty on 2018/12/11.
 */
public class ClassParseEngine {

    public static List<Catagory> parse(List<Class> clazzList){
        List<Catagory> catagoryList = new ArrayList<>();
        List<Method> dubboApiMethodList=getAllDubboApiMethods(clazzList);
        List<ApiDoc> apiDocList=parseMethodList2DubboApiDocList(dubboApiMethodList);
        return convertApiDosList2Catagory(apiDocList);
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
        apiDoc.setType(ProtocalType.DUBBO.toString());
        apiDoc.setCallers(Arrays.asList(dubboApi.caller()));
        List<Parameter> reqParameteList=ParamParseEngine.parse(method.getParameterTypes());
        apiDoc.setReqParamList(reqParameteList);
        List<Parameter> respParamterList=ParamParseEngine.parse(new Class[]{method.getReturnType()});
        apiDoc.setRespParamList(respParamterList);

        apiDoc.setGenericParameterList(getGenericParameterList(reqParameteList,respParamterList));
        return apiDoc;
    }


    private static List<Parameter> getGenericParameterList(List<Parameter> reqParamList,List<Parameter> respParamList){

        Set<String> genericClassNameSet=new LinkedHashSet<>();
        for(Parameter parameter:reqParamList){
            for(ParamField paramField:parameter.getParamFieldList()){
                if(!CollectionUtils.isEmpty(paramField.getRefGenericClassNameList())){
                    genericClassNameSet.addAll(paramField.getRefGenericClassNameList());
                }
            }
        }

        for(Parameter parameter:respParamList){
            for(ParamField paramField:parameter.getParamFieldList()){
                if(!CollectionUtils.isEmpty(paramField.getRefGenericClassNameList())){
                    genericClassNameSet.addAll(paramField.getRefGenericClassNameList());
                }
            }
        }


        List<Class> genericClassList=new ArrayList<>();
        for(String className:genericClassNameSet){
            try{
                genericClassList.add(Class.forName(className));
            }catch (Exception e){

            }
        }

        Class[] genericClasses=new Class[genericClassList.size()];
        for(int i=0;i<genericClassList.size();i++){
            genericClasses[i]=genericClassList.get(i);
        }

        return ParamParseEngine.parse(genericClasses);
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
}
