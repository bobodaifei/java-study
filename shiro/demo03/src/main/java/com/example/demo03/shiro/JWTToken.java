package com.example.demo03.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/*
    JwtToken代替原生的UsernamePasswordToken ,所以要继承AuthenticationToken类
 */
public class JWTToken implements AuthenticationToken {
  private String token;


  public JWTToken(String token) {
    this.token = token;
  }

  //获取用户名 ，这里直接返回token
  @Override
  public Object getPrincipal() {
    return token;
  }

  //返回密码，这里直接返回token
  @Override
  public Object getCredentials() {
    return token;
  }
}