package com.example.demo03.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.bobo.service.HelloService;
import com.example.demo03.entity.User;
import com.example.demo03.service.UserService;
import com.example.demo03.shiro.JWTToken;
import com.example.demo03.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/myController")
public class MyController {

  @Autowired
  HttpSession session;

  @Autowired
  UserService userService;

  @Autowired
  HttpServletRequest request;

  @Autowired
  RedisTemplate<String, String> redisTemplate;

  @GetMapping("/userlogin")
  public String userLogin(String username, String password) {
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(User::getUsername, username);
    User user = userService.getOne(wrapper);
    if (user == null || !password.equals(user.getPassword())) {
      return "登录失败";
    }
    String token = JwtUtil.sign(String.valueOf(user.getId()), user.getUsername(), user.getPassword(), request.getRemoteAddr(), String.valueOf(new Date().getTime()));
    //1获取Subject对象
    Subject subject = SecurityUtils.getSubject();
    //2封装请求数据到token对象中
    JWTToken jwtToken = new JWTToken(token);
    //3调用login方法进行登录认证
    try {
      subject.login(jwtToken);
      redisTemplate.opsForValue().set(token, JSON.toJSONString(user));
      return token;
    } catch (AuthenticationException e) {
      e.printStackTrace();
      System.out.println("登录失败");
      return "登录失败";
    }
  }

  @Autowired
  HelloService helloService;

  @GetMapping("/login")
  public String login() {
    return helloService.sayHello("aoligei");
  }

  //  @RequiresAuthentication//判断是否登录
//  @RequiresUser//是否被记忆
//  @RequiresGuest//是否是一个游客的请求，身份信息为空
//  @RequiresRoles("admin")//是否有对应角色，有角色访问方法
//  @RequiresPermissions("file:read")//是否有对应权限，有权限访问方法
//  @GetMapping("/main")
//  public String main() {
//    session.setAttribute("user", "rememberMe");
//    return "main";
//  }

  //  @RequiresRoles("admin")
  @GetMapping("/roles")
  @ResponseBody
  public String roles() {
    System.out.println("登录角色为admin");
    return "hellp";
  }

  //  @RequiresPermissions("user:update")//是否有对应权限，有权限访问方法
  @GetMapping("/roles0")
  @ResponseBody
  public String roles0() {
    System.out.println("登录权限为user:update");
    return "hellp0";
  }

  //  @RequiresRoles("admin")
//  @RequiresPermissions("user:update")//是否有对应权限，有权限访问方法
  @GetMapping("/roles1")
  @ResponseBody
  public String roles1() {
    System.out.println("登录角色为admin");
    System.out.println("登录权限为user:update");
    return "hellp";
  }
}