package com.gitee.itapm.paser.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jetty on 2018/12/11.
 */
@AllArgsConstructor
@Data
public class Catagory {

    private String catagoryName;

    private List<ApiDoc> apiDocList=new ArrayList<>();

    public Catagory(){

    }
    public Catagory(String catagoryName){
        this.catagoryName=catagoryName;
    }
}
