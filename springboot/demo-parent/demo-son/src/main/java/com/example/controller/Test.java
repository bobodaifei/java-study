package com.example.controller;

import com.example.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {
    @GetMapping("/login")
    public User login() {
        User user = new User();
        user.setName("zhangsan11");
        return user;
    }

}
