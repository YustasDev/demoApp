package com.example.demoapp.controllers;

import com.example.demoapp.service.LimitService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.util.List;


@RestController
@EnableCaching
public class LimitConroller {

    private final Bucket bucket;

    @Autowired
    private LimitService limitService;

//    @Value("${gateway.publicEndpoints}")
//    private List<String> arrEndPoint;

    public LimitConroller(@Value("${capacity.requests}") long capacity) {
        //Bandwidth limit = Bandwidth.classic(capacity, Refill.greedy(capacity, Duration.ofMinutes(1)));
        Bandwidth limit = Bandwidth.classic(capacity, Refill.greedy(capacity, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }


    @CrossOrigin
    @PostMapping("/getCacheData")
    ResponseEntity<?> getCacheData(@RequestParam(name = "intFor_sqrt", required = false) int i){

        if(i>= 0) {
            double cacheData = limitService.getIntData(i);
            limitService.checkCache(i);
            return ResponseEntity.status(HttpStatus.OK).body("Квадратный корень из: '" + i + "' равен '" + cacheData + "'");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Значение поступившей переменной <0");
        }
    }

    @CrossOrigin
    @GetMapping("/get_data")
    ResponseEntity<?> getData() {
        String my_data = "it's OK";

//            String x1 = arrEndPoint.get(0);
//            String x2 = arrEndPoint.get(1);
//            System.out.println(x1);
//            System.out.println(x2);

        System.out.println(System.getenv("SHELL"));

        String receiveData = limitService.forGetData("1");
        System.out.println(receiveData);

        Integer lostStr = limitService.lostMethod(13);
        System.err.println(lostStr);



        if (bucket.tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.OK).body(my_data);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");
    }


    @CrossOrigin
    @GetMapping("/get_another_data")
    ResponseEntity<?> getAnotherData() {
        String my_data = "it's another OK";
        String receiveData1 = limitService.forAnoterGetData();
        System.out.println(receiveData1);

        if (bucket.tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.OK).body(my_data);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");
    }


}
