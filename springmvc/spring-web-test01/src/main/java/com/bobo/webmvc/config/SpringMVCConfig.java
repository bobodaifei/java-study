package com.bobo.webmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//<context:component-scan base-package="com.bobo.webmvc.controller"/>
@ComponentScan("com.bobo.webmvc.controller")
@Import(MyWebMvcConfigurer.class)
//启用处理器映射器适配器等、访问静态资源、拦截器
@EnableWebMvc
public class SpringMVCConfig {

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

}
