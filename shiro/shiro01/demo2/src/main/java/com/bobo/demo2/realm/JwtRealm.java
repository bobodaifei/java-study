package com.bobo.demo2.realm;

import com.bobo.demo2.entity.User;
import com.bobo.demo2.service.UserService;
import com.bobo.demo2.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtRealm extends AuthorizingRealm {

  @Autowired
  UserService userService;

  //根据token判断此Authenticator是否使用该realm
  //必须重写不然shiro会报错
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JWTToken;
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    //获取当前用户身份信息
    String token = principals.getPrimaryPrincipal().toString();
    String name = JwtUtil.verifyGetUserName(token);
    //调用接口方法获取用户的角色信息
    List<String> roles = userService.getUserRoleInfo(name);
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

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    System.out.println("————身份认证方法————");
    String token = authenticationToken.getCredentials().toString();
    // 解密获得username，用于和数据库进行对比
    String userId = JwtUtil.verifyGetUserId(token);
    User user = userService.getById(userId);
    if (user == null) {
      throw new AuthenticationException("用户不存在");
    }
    //如果用户信息不为空，将用户身份信息、用户密码、盐值（salt）以及当前 realm 的名称封装到
    return new SimpleAuthenticationInfo(token, token, "JwtRealm");
  }
}
