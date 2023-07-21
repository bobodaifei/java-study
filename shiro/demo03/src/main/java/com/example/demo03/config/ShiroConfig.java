package com.example.demo03.config;

import com.example.demo03.shiro.JwtFilter;
import com.example.demo03.shiro.JwtRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

  @Autowired
  private JwtRealm jwtRealm;

  @Bean
  public DefaultWebSecurityManager securityManager() {
    DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
    //配置realm
    defaultWebSecurityManager.setRealm(jwtRealm);
    //关闭shiro自带的session
    DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
    defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
    subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
    defaultWebSecurityManager.setSubjectDAO(subjectDAO);
    return defaultWebSecurityManager;
  }

    @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
      ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

      // 添加自己的过滤器并且取名为token
      Map<String, Filter> filterMap = new HashMap<>();
      filterMap.put("token", new JwtFilter());
      factoryBean.setFilters(filterMap);

      factoryBean.setSecurityManager(securityManager);
      /*
       * 自定义url规则
       * http://shiro.apache.org/web.html#urls-
       */
      Map<String, String> filterRuleMap = new LinkedHashMap<>();
      //登录
      filterRuleMap.put("/myController/userlogin", "anon");
      filterRuleMap.put("/myController/login", "anon");
      // 所有请求通过我们自己的JWT Filter
      filterRuleMap.put("/**", "token");
      factoryBean.setFilterChainDefinitionMap(filterRuleMap);
      return factoryBean;
  }

  //配置Shiro内置过滤器拦截范围
//  @Bean
//  public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager
//          , ShiroFilterChainDefinition shiroFilterChainDefinition, FilterRegistrationBean jwtFilterRegBean) {
//    ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
//    factoryBean.setSecurityManager(securityManager);
//    Map<String, Filter> filterMap = new LinkedHashMap<>(8);
//    filterMap.put("jwt", jwtFilterRegBean.getFilter());
//    factoryBean.setFilters(filterMap);
//    factoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
//    return factoryBean;
//  }
//
//  /**
//   * 在此处配置过滤器链
//   */
//  @Bean
//  public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//    DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//    //这些请求不通过jwtFilter
//    chainDefinition.addPathDefinition("/myController/userlogin", "anon");
//    chainDefinition.addPathDefinition("/myController/login", "anon");
//    // 所有请求通过我们自己的JWT Filter
//    chainDefinition.addPathDefinition("/**", "authc");
//    return chainDefinition;
//  }
//
//  /**
//   * 配置JwtFilter过滤器,并设置为未注册状态
//   */
//  @Bean
//  public FilterRegistrationBean jwtFilterRegBean() {
//    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//    //添加JwtFilter  并设置为未注册状态
//    filterRegistrationBean.setFilter(new JwtFilter());
//    filterRegistrationBean.setEnabled(false);
//    return filterRegistrationBean;
//  }


  //配置 AuthorizationAttributeSourceAdvisor 使 doGetAuthorizationInfo（）Shiro 权限配置生效
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }

  //  解决 在 @Controller 注解的类的方法中加入 @RequiresRole 等 shiro 注解，会导致该方法无法映射请求，导致返回 404。
  @Bean
  public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    /**
     * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
     * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
     * 加入这项配置能解决这个bug
     */
    defaultAdvisorAutoProxyCreator.setUsePrefix(true);
    return defaultAdvisorAutoProxyCreator;
  }

}
