package com.example.redis.config;

import com.example.redis.utils.reids.RedisClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {

    List<String> address;

    @Bean
    public RedisClient redisClient() {
        System.out.println(address);
        return new RedisClient(address);
    }

}
