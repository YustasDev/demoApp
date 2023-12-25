package com.example.demoapp.service;

import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
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

    @Cacheable(cacheNames = "sqrt_cach", condition="#i>= 10")
    public double getIntData(int i){
        double output = Math.sqrt(i);
        return output;
    }

    public Integer lostMethod(Integer data2){
        return data2;
    }



}
