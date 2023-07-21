package com.bobo.purview;

import com.bobo.purview.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@MapperScan("com.bobo.purview.mapper")
public class PurviewApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext run = SpringApplication.run(PurviewApplication.class, args);
    RedisTemplate<String, User> redisTemplate = run.getBean("redisTemplate", RedisTemplate.class);
    User user = new User();
    user.setUserId(1);
    redisTemplate.opsForValue().set("token", user);

    RedisTemplate<String, String> redisTemplate1 = run.getBean("redisTemplate", RedisTemplate.class);
    System.out.println(redisTemplate1.opsForValue().get("token"));


  }

}
