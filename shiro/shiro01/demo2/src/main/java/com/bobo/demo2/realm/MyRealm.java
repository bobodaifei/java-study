package com.bobo.demo2.realm;

import com.bobo.demo2.entity.User;
import com.bobo.demo2.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {

  @Autowired
  private UserService userService;

  //自定义授权方法
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    System.out.println("自定义授权方法");
    //获取当前用户身份信息
    String principal = principals.getPrimaryPrincipal().toString();
    //调用接口方法获取用户的角色信息
    List<String> roles = userService.getUserRoleInfo(principal);
    System.out.println("当前用户角色信息：" + roles);
    //调用接口方法获取用户角色的权限信息
    List<String> permissions = userService.getUserPermissionInfo(roles);
    System.out.println("当前用户权限信息：" + permissions);
    //创建对象，存储当前登录的用户的权限和角色
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    //存储角色
    info.addRoles(roles);
    //存储权限信息
    info.addStringPermissions(permissions);
    //返回
    return info;
  }

  //自定义登录认证方法
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    //1获取用户身份信息
    String name = token.getPrincipal().toString();
    //2调用业务层获取用户信息（数据库中）
    User user = userService.getUserInfoByName(name);
    //3判断并将数据完成封装
    if (user != null) {
      //如果用户信息不为空，将用户身份信息、用户密码、盐值（salt）以及当前 realm 的名称封装到
      return new SimpleAuthenticationInfo(token.getPrincipal(), user.getPwd(), ByteSource.Util.bytes("salt"), "MyRealm");
    }
    return null;
  }
}
