package com.bobo.mongodb01;

import com.bobo.mongodb01.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
class Mongodb01ApplicationTests {

  @Autowired
  MongoTemplate mongoTemplate;

  @Test
  void contextLoads() {
    User user = new User();
    user.setUserId(1);
    user.setUserName("bobo");
    user.setPassword("123456");
    mongoTemplate.save(user);

    List<User> all = mongoTemplate.findAll(User.class);
    System.out.println(all);

  }

}
