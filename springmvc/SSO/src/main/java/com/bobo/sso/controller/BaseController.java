package com.bobo.sso.controller;


import com.auth0.jwt.JWT;
import com.bobo.sso.pojo.vo.UserVO;
import com.bobo.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {

  @Autowired
  private UserService userService;

  @Autowired
  private HttpServletRequest request;

  /**
   * 根据token获取用户信息
   *
   * @return user
   */
  public UserVO getUser(){
    String token = request.getHeader("Access-Token");
    Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
    return userService.selectById(userId);
  }
}
