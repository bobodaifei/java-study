package com.bobo.cache1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Cache1Application {

    public static void main(String[] args) {
        SpringApplication.run(Cache1Application.class, args);
    }

}
