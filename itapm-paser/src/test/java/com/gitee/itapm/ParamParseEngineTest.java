package com.gitee.itapm;

import com.gitee.itapm.annotations.enums.ParamDirectoryType;
import com.gitee.itapm.bean.TestBean;
import com.gitee.itapm.paser.ParamParseEngine;
import com.gitee.itapm.paser.bean.ParamField;
import com.gitee.itapm.paser.bean.Parameter;
import org.junit.Test;

import java.util.List;

/**
 * Created by jetty on 2018/12/11.
 */
public class ParamParseEngineTest {

    @Test
    public void test(){
        List<Parameter> params= ParamParseEngine.parse(new Class[]{TestBean.class});
        for(Parameter param: params){
            System.out.println(param);
        }
    }
}
