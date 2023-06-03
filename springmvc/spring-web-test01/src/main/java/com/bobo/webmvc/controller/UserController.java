package com.bobo.webmvc.controller;

import com.bobo.webmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/login")
  public String login() {
    userService.selectAll();
    return "index.jsp";
  }
}
