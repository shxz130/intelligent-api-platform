package com.gitee.itapm.web.controller.restful;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.gitee.itapm.utils.page.ResultBean;
import com.gitee.itapm.web.controller.bean.DubboRequest;
import com.gitee.itapm.web.controller.bean.RestRequest;
import com.gitee.itapm.web.utils.UrlPathConstants;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jetty on 2018/12/26.
 */
@RestController
public class DubboTestController {


    /**
     * facade#method#group#version#zk地址 泛化服务
     */
    private static final Map<String,GenericService> serviceMap=new ConcurrentHashMap<>();


    @PostMapping(UrlPathConstants.ITAPM_DUBBO_TEST_URL)
    public ResultBean<String> dubboTestUrl(@RequestBody DubboRequest request){
        try{
            String key=getKey(request);
            String method=request.getInterfaceName().substring(request.getInterfaceName().lastIndexOf("#") + 1);
            GenericService genericService;
            if(serviceMap.get(key)!=null){
                genericService=serviceMap.get(key);
            }else{
                genericService =createService(request);
                if(genericService!=null){
                    serviceMap.put(key,genericService);
                }
            }
            Map<String,Object> dataMap=new HashMap<>();
            if(StringUtils.isNotBlank(request.getRequestData())){
                dataMap=JSON.parseObject(request.getRequestData(),HashMap.class);
            }
            Object result=genericService.$invoke(method, new String[]{request.getRequestParamType()}, new Object[]{dataMap});
            return new ResultBean<>(JSON.toJSONString(result));
        }catch (Exception e){
            return new ResultBean(getStackTrace(e));
        }

    }


    private GenericService createService(DubboRequest request){
        ApplicationConfig application=new ApplicationConfig();
        application.setName("itapm");
        RegistryConfig registryConfig =new RegistryConfig();
        registryConfig.setAddress(request.getZookeeper());
        ReferenceConfig<GenericService> rc = new ReferenceConfig<GenericService>();
        rc.setApplication(application);
        rc.setGeneric(true);
        String interfaceName=request.getInterfaceName().substring(request.getInterfaceName().lastIndexOf("#")+1);
        rc.setInterface(interfaceName);
        rc.setRegistry(registryConfig);
        rc.setGroup(request.getGroup());
        rc.setVersion(request.getVersion());
        return rc.get();
    }

    private String getKey(DubboRequest request){
        String interfaceName=request.getInterfaceName().substring(0,request.getInterfaceName().lastIndexOf("#"));
        return String.format("%s#%s#%s",interfaceName,request.getGroup(),request.getVersion(),request.getZookeeper());
    }



    private String getResponseData(HttpResponse httpResponse){
        List<HttpCookie> cookieList=httpResponse.getCookies();
        Map<String,String> map=new HashMap<>();
        String cookieString=null;
        if(!CollectionUtils.isEmpty(cookieList)){
            for(HttpCookie httpCookie:cookieList){
                map.put(httpCookie.getName(),String.format("value=%s,path=%s,domain=%s", httpCookie.getValue(), httpCookie.getPath(), httpCookie.getDomain()));
            }
            cookieString= Joiner.on(";").withKeyValueSeparator(":").join(map);
        }
        String body=httpResponse.body();
        return String.format("cookies:\n%s\nbody:\n%s",cookieString,body);
    }




    private void setHeader(HttpRequest httpRequest,String headerString){
       if(StringUtils.isBlank(headerString)){
           return;
       }
       Map<String,String> dataMap= Splitter.on(";").withKeyValueSeparator("=").split(headerString);
       for(Map.Entry entry:dataMap.entrySet()){
           httpRequest.header((String)entry.getKey(),(String)entry.getValue());
       }

    }


    private void setCookies(HttpRequest httpRequest,String cookiesString){
        if(StringUtils.isBlank(cookiesString)){
            return;
        }
        Map<String,String> dataMap= Splitter.on(";").withKeyValueSeparator("=").split(cookiesString);
        HttpCookie[] httpCookies=new HttpCookie[dataMap.entrySet().size()];
        int i=0;
        for(Map.Entry entry:dataMap.entrySet()){
            HttpCookie httpCookie=new HttpCookie((String)entry.getKey(),(String)entry.getValue());
            httpCookies[i]=httpCookie;
        }
        httpRequest.cookie(httpCookies);
    }


    private static String getStackTrace(Throwable t)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try{
            t.printStackTrace(pw);
            return sw.toString();
        }
        finally{
            pw.close();
        }



    }

}
