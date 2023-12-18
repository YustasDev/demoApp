package com.example.demoapp.aop_clacces;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* *.forGetData(..))")
    public void logBeforeTransfer(){
        System.out.println("Произошел вызов метода getData()");
    }


    @After("execution(* *.forAnoterGetData())")
    public void logAfterTransfer(){
        System.out.println("Произошел вызов метода getAnotherData()");
    }






}
