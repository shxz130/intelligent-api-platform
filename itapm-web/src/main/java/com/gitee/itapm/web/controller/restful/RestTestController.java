package com.gitee.itapm.web.controller.restful;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.gitee.itapm.utils.page.ResultBean;
import com.gitee.itapm.web.controller.bean.RestRequest;
import com.gitee.itapm.web.utils.UrlPathConstants;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by jetty on 2018/12/26.
 */
@RestController
public class RestTestController {


    @PostMapping(UrlPathConstants.ITAPM_REST_TEST_URL)
    public ResultBean<String> restTestUrl(@RequestBody RestRequest request){

        try{
            String url=request.getRequestBaseUrl()+ request.getRequestRelativeUrl();
            HttpRequest httpRequest=new HttpRequest(url);
            setHeader(httpRequest, request.getHeaders());
            setCookies(httpRequest, request.getCookies());
            HttpResponse httpResponse=null;
            Map<String,Object> dataMap=new HashMap<>();
            String data=request.getRequestData();
            if(StringUtils.isNotBlank(request.getRequestData())){
                dataMap=JSON.parseObject(request.getRequestData(), Map.class);
            }
            if(request.getRequestMethod().equals("GET")){
                httpResponse= httpRequest.method(Method.GET).form(dataMap).timeout(3000).execute();
            }else if(request.getRequestMethod().endsWith("POST")){
                httpResponse= httpRequest.method(Method.POST).form(dataMap).timeout(3000).execute();
            }
            return new ResultBean( getResponseData(httpResponse));
        }catch (Exception e){
            return new ResultBean(getStackTrace(e));
        }




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
        httpRequest.cookie(cookiesString);
      /*  if(StringUtils.isBlank(cookiesString)){
            return;
        }
        Map<String,String> dataMap= Splitter.on(";").withKeyValueSeparator("=").split(cookiesString);
        HttpCookie[] httpCookies=new HttpCookie[dataMap.entrySet().size()];
        int i=0;
        for(Map.Entry entry:dataMap.entrySet()){
            HttpCookie httpCookie=new HttpCookie((String)entry.getKey(),(String)entry.getValue());
            httpCookies[i]=httpCookie;
        }
        httpRequest.cookie(httpCookies);*/
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
