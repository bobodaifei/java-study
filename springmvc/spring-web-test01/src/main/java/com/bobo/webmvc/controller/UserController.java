package com.bobo.webmvc.controller;

import com.bobo.webmvc.entity.User;
import com.bobo.webmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/login")
  public String login(HttpServletRequest request) {
    ServletContext servletContext = request.getServletContext();
    System.out.println(servletContext);
    userService.selectAll();
    return "index";
  }

  @PostMapping("/update")
  public String update(@RequestBody User user) {
    System.out.println(user);
    return "index";
  }

}

