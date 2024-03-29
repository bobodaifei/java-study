package com.bobo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthenticatingRealm {

  //自定义的登录认证方法，Shiro的login方法底层会调用该类的认证方法完成登录认证
  // 需要配置自定义的realm生效，在ini文件中配置，或Springboot中配置
  // 该方法只是获取进行对比的信息，认证逻辑还是按照Shiro的底层认证逻辑完成认证

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

    //1获取身份信息
    String principal = token.getPrincipal().toString();
    //2获取凭证信息
    String password = new String((char[]) token.getCredentials());
    System.out.println("认证用户信息：" + principal + "---" + password);
    //3获取数据库中存储的用户信息
    if (principal.equals("zhangsan")) {
      //3.1数据库存储的加盐迭代3次密码
      String pwdInfo = "faad7bdc17dc4cbcffe4e916a3b49b10";
      //3.2创建封装了校验逻辑的对象，将要比较的数据给该对象
      AuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(), pwdInfo, ByteSource.Util.bytes("salt1"), token.getPrincipal().toString());
      return info;
    }
    return null;
  }
}
