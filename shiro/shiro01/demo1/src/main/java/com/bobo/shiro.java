package com.bobo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class shiro {
  public static void main(String[] args) {
    //1初始化获取SecurityManager
    IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
    //取得相关实例，拿到认证管理器
    SecurityManager securityManager = factory.getInstance();
    SecurityUtils.setSecurityManager(securityManager);
    //2获取Subject对象
    Subject subject = SecurityUtils.getSubject();
    //3创建token对象，web应用用户名密码从页而传递
    AuthenticationToken token = new UsernamePasswordToken("zhangsan","z3");
    //4完成登
    try {
      subject.login(token);
      System.out.println("success");
      //5判断角色
      boolean b = subject.hasRole("role12");
      System.out.println(b);
      //6判断权限
      boolean permitted = subject.isPermitted("user:insert1");
      System.out.println(permitted);
    }catch (UnknownAccountException e){
      System.out.println("用户不存在");
    }catch (IncorrectCredentialsException e){
      System.out.println("密码错误");
    }catch (AuthenticationException e){
      System.out.println("error");
    }
  }
}
