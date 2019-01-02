package com.gitee;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gitee.itapm.bean.BaseResp;
import com.gitee.itapm.bean.TransInfo;

/**
 * Created by jetty on 2018/12/24.
 */
public class Test {


    @org.junit.Test
    public void test(){
        try{
            BaseResp<TransInfo> bas=BaseResp.class.newInstance();
            System.out.println(JSON.toJSONString(bas, SerializerFeature.WriteMapNullValue));
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
