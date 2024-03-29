package com.bobo.config;

import com.bobo.interceptors.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//<context:component-scan base-package="com.bobo.webmvc.controller"/>
@ComponentScan({"com.bobo.controller", "com.bobo.interceptors", "com.bobo.exception"})
//启用处理器映射器适配器等、访问静态资源、拦截器
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {

  @Autowired
  AuthInterceptor authInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/user/login", "/user/register");
  }

/*  <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <property name="defaultEncoding" value="UTF=8"/>
    <property name="maxUploadSizePerFile" value="1048576"/>
    <property name="maxUploadSize" value="3145728"/>
    <property name="maxInMemorySize" value="1048576"/>
  </bean>*/

  @Bean
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setDefaultEncoding("UTF-8");
    multipartResolver.setMaxUploadSizePerFile(1048576);
    multipartResolver.setMaxUploadSize(3145728);
    multipartResolver.setMaxInMemorySize(1048576);
    return multipartResolver;
  }

  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/views/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    //开启DefaultServletHandling
    configurer.enable();
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // 所有接口
            .allowCredentials(true) // 是否发送 Cookie
            .allowedOrigins("*") // 支持域
            .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}) // 支持方法
            .allowedHeaders("*");
  }

  //1、WebMvcConfigurer定制化SpringMVC的功能
  


}
