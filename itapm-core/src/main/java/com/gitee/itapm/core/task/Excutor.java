package com.gitee.itapm.core.task;

import com.gitee.itapm.core.task.api.ApiPersistAction;
import com.gitee.itapm.utils.spring.SpringContextUtils;
import com.gitee.itapm.paser.bean.Document;

import java.util.concurrent.*;

/**
 * Created by jetty on 2018/12/11.
 */
public class Excutor {


    private static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(10, 15,10,
            TimeUnit.MINUTES,  new LinkedBlockingDeque<>(100), new ThreadPoolExecutor.AbortPolicy());




    public static Future submit(Document document){
        return threadPoolExecutor.submit(new Thread(){
            @Override
            public void run() {
                ApiPersistAction apiPersistAction= (ApiPersistAction)SpringContextUtils.getBeanByName("apiPersistAction");
                apiPersistAction.persist(document);
            }
        });
    }




}
