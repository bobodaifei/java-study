package com.bobo.demo2.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bobo.demo2.entity.User;
import com.bobo.demo2.realm.JWTToken;
import com.bobo.demo2.service.UserService;
import com.bobo.demo2.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/myController")
public class MyController {

  @Autowired
  HttpSession session;

  @Autowired
  UserService userService;

  @Autowired
  HttpServletRequest request;

  @GetMapping("/userlogin")
  @ResponseBody
  public String userLogin(String name, String pwd, @RequestParam(defaultValue = "false") boolean rememberMe) {
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(User::getName, name);
    User user = userService.getOne(wrapper);

    if (user == null || !pwd.equals(user.getPwd())) {
      return "登录失败";
    }
    String token = JwtUtil.sign(String.valueOf(user.getId()), user.getName(), user.getPwd(), request.getRemoteAddr(), String.valueOf(new Date().getTime()));
    System.out.println(token);

    //1获取Subject对象
    Subject subject = SecurityUtils.getSubject();
    //2封装请求数据到token对象中
    JWTToken jwtToken = new JWTToken(token, rememberMe);
//    AuthenticationToken token = new UsernamePasswordToken(name, pwd, rememberMe);
    //3调用login方法进行登录认证
    try {
//      subject.login(token);
      subject.login(jwtToken);
//      session.setAttribute("user", token.getPrincipal().toString());
      return token;
    } catch (AuthenticationException e) {
      e.printStackTrace();
      System.out.println("登录失败");
      return "登录失败";
    }
  }

  @GetMapping("/login")
  public String login() {
    return "login";
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

  @RequiresRoles("admin")
  @GetMapping("/roles")
  @ResponseBody
  public String roles() {
    System.out.println("登录角色为admin");
    return "hellp";
  }

  @RequiresPermissions("user:update")//是否有对应权限，有权限访问方法
  @GetMapping("/roles0")
  @ResponseBody
  public String roles0() {
    System.out.println("登录权限为user:update");
    return "hellp0";
  }

  @RequiresRoles("admin")
  @RequiresPermissions("user:update")//是否有对应权限，有权限访问方法
  @GetMapping("/roles1")
  @ResponseBody
  public String roles1() {
    System.out.println("登录角色为admin");
    System.out.println("登录权限为user:update");
    return "hellp";
  }
}