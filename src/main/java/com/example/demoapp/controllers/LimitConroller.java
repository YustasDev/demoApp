package com.example.demoapp.controllers;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;


@RestController
public class LimitConroller {

    private final Bucket bucket;

    public LimitConroller(@Value("${capacity.requests}") long capacity) {
        //Bandwidth limit = Bandwidth.classic(capacity, Refill.greedy(capacity, Duration.ofMinutes(1)));
        Bandwidth limit = Bandwidth.classic(capacity, Refill.greedy(capacity, Duration.ofSeconds(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @CrossOrigin
    @GetMapping("/get_data")
    ResponseEntity<?> getData() {
        String my_data = "it's OK";

        if (bucket.tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.OK).body(my_data);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");
    }



}
