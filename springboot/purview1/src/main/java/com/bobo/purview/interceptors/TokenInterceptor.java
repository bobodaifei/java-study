package com.bobo.purview.interceptors;

import com.bobo.purview.entity.User;
import com.bobo.purview.exception.CustomException;
import com.bobo.purview.service.UserService;
import com.bobo.purview.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

  @Autowired
  UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("Access-Token");
    String userId = JwtUtil.verifyGetUserId(token);
    if (userId == null) {
      throw new CustomException("401", "token不合法");
    }
    User user = userService.getById(Integer.valueOf(userId));
    if (user == null) {
      throw new CustomException("401", "token不合法");
    }
    request.setAttribute("userId",userId);
    return true;

  }
}
