package com.example.demo03.shiro;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.example.demo03.base.Result;
import com.example.demo03.entity.User;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
 * isAccessAllowed（） 判断是否可以登录到系统
 *
 *onAccessDenied（） 当 isAccessAllowed（）返回 false 时，登录被拒绝，进入此接口进行异常处理
 * */
@Component
public class JwtFilter extends FormAuthenticationFilter {

  private Result result;

  @Autowired
  RedisTemplate<String, String> redisTemplate;

  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String token = httpServletRequest.getHeader("token");
    if (StrUtil.isBlank(token)) {
      this.result = Result.error("401", "token不存在");
      return false;
    }
    User user = null;
    try {
      user = JSON.parseObject(redisTemplate.opsForValue().get(token), User.class);
    } catch (Exception e) {
      this.result = Result.error("401", "token无效");
      return false;
    }
    if (user == null) {
      this.result = Result.error("401", "token无效");
      return false;
    }
    //刷新超时时间
    redisTemplate.expire(token, 30 * 60, TimeUnit.SECONDS); //30分钟过期
    JWTToken jwtToken = new JWTToken(token);
    // 提交给realm进行登入，如果错误他会抛出异常并被捕获
    getSubject(request, response).login(jwtToken);
    // 如果没有抛出异常则代表登入成功，返回true
    return true;
  }

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    String reponseJson = JSON.toJSONString(this.result);
    response.setContentType("application/json; charset=utf-8");
    response.setCharacterEncoding("utf-8");
    ServletOutputStream outputStream = null;
    try {
      outputStream = response.getOutputStream();
      outputStream.write(reponseJson.getBytes());
    } catch (IOException e) {
      //身份验证失败后IO写出异常
      //log.error("权限校验异常",e);
    } finally {
      if (outputStream != null) {
        try {
          outputStream.flush();
          outputStream.close();
        } catch (IOException e) {
          //身份验证失败后IO关闭异常
          //log.error("权限校验,关闭连接异常", e);
        }
      }
    }
    return false;
  }
}
