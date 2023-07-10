package com.bobo.bshop.controller;

import com.bobo.bshop.component.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/token")
public class TokenController {

  @Autowired
  HttpServletRequest request;

  @PostMapping
  public Result<?> returnToken() {
    System.out.println("来啦");
    return Result.success(request.getAttribute("token"));
  }
}
