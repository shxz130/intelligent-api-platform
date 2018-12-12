package com.gitee.itapm;

import com.alibaba.fastjson.JSON;
import com.gitee.itapm.annotations.DubboApi;
import com.gitee.itapm.bean.DubboApiFacade;
import com.gitee.itapm.paser.ClassParseEngine;
import com.gitee.itapm.paser.bean.Catagory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jetty on 2018/12/12.
 */
public class ClassParseEngineTest {

    @Test
    public void test(){

        List<Class> classList= new ArrayList<>();
        classList.add(DubboApiFacade.class);
        List<Catagory> catagoryList=ClassParseEngine.parse(classList);
        System.out.println(JSON.toJSON(catagoryList));
    }
}
