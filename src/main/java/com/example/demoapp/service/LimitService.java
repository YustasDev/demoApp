package com.example.demoapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LimitService {

    public String forGetData(String data){
        if(data.length() == 0){
        throw new RuntimeException("It's RuntimeException");}
        else {
        return data;}
    }

    public String forAnoterGetData(){
        String data1 = "26";
        return data1;
    }

    public Integer lostMethod(Integer data2){
        return data2;
    }



}
