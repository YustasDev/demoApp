package com.example.demoapp.service;


import org.springframework.stereotype.Component;

@Component
public class LimitService {

    public String forGetData(String data){
        return data;
    }

    public String forAnoterGetData(){
        String data1 = "26";
        return data1;
    }



}
