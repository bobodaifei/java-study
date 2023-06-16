package com.bobo.interceptors;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.bobo.entity.User;
import com.bobo.exception.CustomException;
import com.bobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

  @Autowired
  private UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("Access-Token");
    if (StrUtil.isBlank(token)) {
      throw new CustomException("401", "未获取到token, 请重新登录");
    }
    String userId = JWT.decode(token).getAudience().get(0);
    User user = userService.selectById(userId);
    if (user == null) {
      throw new CustomException("401", "token不合法");
    }
    // 验证 token
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
    try {
      jwtVerifier.verify(token);
    } catch (Exception e) {
      throw new CustomException("401", "token不合法");
    }
    return true;
  }
}
