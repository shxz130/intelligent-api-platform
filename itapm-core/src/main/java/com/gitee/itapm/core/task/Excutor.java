package com.gitee.itapm.core.task;

import com.gitee.itapm.core.task.api.ApiPersistAction;
import com.gitee.itapm.utils.spring.SpringContextUtils;
import com.gitee.itapm.paser.bean.Document;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by jetty on 2018/12/11.
 */
@Slf4j
public class Excutor {


    private static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(10, 15,10,
            TimeUnit.MINUTES,  new LinkedBlockingDeque<>(100), new ThreadPoolExecutor.AbortPolicy());




    public static Future submit(Document document){
        long start=System.currentTimeMillis();
        return threadPoolExecutor.submit(new Thread(){
            @Override
            public void run() {
                log.info("document开始解析:{}",document);
                try{
                    ApiPersistAction apiPersistAction= (ApiPersistAction)SpringContextUtils.getBeanByName("apiPersistAction");
                    apiPersistAction.handle(document);
                }catch (Exception e){
                    log.error("",e);
                }
                log.info("document解析完成，耗时:{}ms",System.currentTimeMillis()-start);

            }
        });
    }




}
