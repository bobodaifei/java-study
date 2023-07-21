package com.bobo.demo2.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.bobo.demo2.realm.JwtRealm;
import com.bobo.demo2.realm.MyRealm;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ShiroConfig {

  @Autowired
  private MyRealm myRealm;

  @Autowired
  private JwtRealm jwtRealm;

  //配置SecurityManager
//  @Bean
//  public DefaultWebSecurityManager defaultWebSecurityManager() {
//    //1创建defaultWebSecurityManager对象
//    DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//    //2创建加密对象，并设置相关属性
//    HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//    //2.1采用md5加密
//    matcher.setHashAlgorithmName("md5");
//    //2.2迭代加密次数
//    matcher.setHashIterations(3);
//    //3将加密对象存储到myRealm中
//    myRealm.setCredentialsMatcher(matcher);
//    //4将myRealm存入defaultWebSecurityManager对象
//    defaultWebSecurityManager.setRealm(myRealm);
//    //4.5设置rememberMe
//    defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
//    //4.6设置缓存管理器
//    defaultWebSecurityManager.setCacheManager(getEhCacheManager());
//    //5返回
//    return defaultWebSecurityManager;
//  }

  @Bean
  public DefaultWebSecurityManager defaultWebSecurityManager() {
    DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
    //关闭shiro自带的session
    DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
    defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
    subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
    defaultWebSecurityManager.setSubjectDAO(subjectDAO);
    //加入缓存
    defaultWebSecurityManager.setCacheManager(getEhCacheManager());
    //配置realm
    defaultWebSecurityManager.setRealm(jwtRealm);
    return defaultWebSecurityManager;
  }

  //创建Shiro的cookie管理对象
  public CookieRememberMeManager rememberMeManager() {
    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
    cookieRememberMeManager.setCookie(rememberMeCookie());
//    必须是16位
    cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
    return cookieRememberMeManager;
  }

  //cookie属性设置
  public SimpleCookie rememberMeCookie() {
    SimpleCookie cookie = new SimpleCookie("rememberMe");
    //设置跨域//
//    cookie.setDomain(domain);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(30 * 24 * 60 * 60);
    return cookie;
  }

  //缓存管理器
  public EhCacheManager getEhCacheManager() {
    EhCacheManager ehCacheManager = new EhCacheManager();
    InputStream is = null;
    try {
      is = ResourceUtils.getInputStreamForPath("classpath:ehcache/ehcache-shiro.xml");
    } catch (
            IOException e) {
      e.printStackTrace();
    }
    CacheManager cacheManager = new CacheManager(is);
    ehCacheManager.setCacheManager(cacheManager);
    return ehCacheManager;
  }


  //配置Shiro内置过滤器拦截范围
  @Bean
  public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
    DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
    //设置不认证可以访问的资源
    definition.addPathDefinition("/myController/userlogin", "anon");
    definition.addPathDefinition("/myController/login", "anon");
    //配置登出过滤器
    definition.addPathDefinition("/logout", "logout");
    //设置需要进行登录认证的拦截范围
//    1.过滤器要求用户进行登录认证。
    definition.addPathDefinition("/**", "authc");
//    2.过滤器要求用户进行登录认证，并且是已被记住的用户。
//    definition.addPathDefinition("/**", "user");
    return definition;
  }

  @Bean
  public ShiroDialect shiroDialect() {
    return new ShiroDialect();
  }


}
