package com.bobo.sso.component.config;

import com.bobo.sso.interceptors.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//<context:component-scan base-package="com.bobo.webmvc.controller"/>
@ComponentScan({"com.bobo.sso.controller", "com.bobo.sso.interceptors", "com.bobo.sso.exception"})
//启用处理器映射器适配器等、访问静态资源、拦截器
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {

  @Autowired
  AuthInterceptor authInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/sso/login","/sso/verify");
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    //开启DefaultServletHandling
    configurer.enable();
  }

}
