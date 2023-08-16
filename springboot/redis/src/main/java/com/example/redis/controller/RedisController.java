package com.example.redis.controller;

import com.example.redis.utils.reids.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    RedisClient redisClient;

    @GetMapping("/getRedis/{key}")
    public String getRedis(@PathVariable String key) {
        return redisClient.get(key);
    }

    @GetMapping("/setRedis/{key}/{value}")
    public String setRedis(@PathVariable String key, @PathVariable String value) {
        return redisClient.set(key, value);
    }

}
