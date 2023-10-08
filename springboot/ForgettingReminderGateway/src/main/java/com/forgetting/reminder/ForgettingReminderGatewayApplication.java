package com.forgetting.reminder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.forgetting.reminder.mapper")
public class ForgettingReminderGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForgettingReminderGatewayApplication.class, args);
    }

}
