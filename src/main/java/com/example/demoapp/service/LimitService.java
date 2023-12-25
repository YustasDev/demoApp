package com.example.demoapp.service;

import com.example.demoapp.config.CacheManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class LimitService {

    @Autowired
    private CacheManagement cacheManagement;

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

    @Cacheable(cacheNames = "cache1", key = "#i", condition="#i>= 10", cacheManager = "my-cache-manager")
    public double getIntData(int i){
        double output = Math.sqrt(i);
        return output;
    }

    public void checkCache(int i){
        Object cacheData = null;

        {
            cacheData = cacheManagement.cacheManager().getCache("cache1").get(i).get();
        }

        System.out.println("Значение mydata = " + cacheData);

    }



    public Integer lostMethod(Integer data2){
        return data2;
    }



}
