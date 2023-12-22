package com.example.demoapp.aop_clacces;


import com.example.demoapp.service.LimitService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {

    @Autowired
    private LimitService limitService;

    @Before("execution(* *.forGetData(..))")
    private void logBeforeTransfer(){
        System.out.println("Произошел вызов метода getData()");
    }

    @AfterThrowing("execution(* com.example.demoapp.service.LimitService.forGetData(..))")
    private void rollback() {
        System.out.println("Произошла ошибка при выполнении LimitService.forGetData()");
    }


    @After("execution(* *.forAnoterGetData())")
    private void logAfterTransfer(){
        System.out.println("Произошел вызов метода getAnotherData()");
    }


    @Around("execution(public * com.example.demoapp.service.LimitService.lostMethod(..))")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        Integer retVal = (Integer) joinPoint.proceed();
        Object obj = joinPoint.getArgs();
        if (retVal<42){
            System.out.println("Произошел вызов метода lostMethod(..)");
            return null;
        }
        else {return retVal;}
    }





}
