package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoContsonTestApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoContsonTestApplication.class);

    public static void main(String[] args) {
        logger.info("这是一条普通日志信息");
        logger.error("这是一条错误日志信息");

        SpringApplication.run(DemoContsonTestApplication.class, args);
    }

}
