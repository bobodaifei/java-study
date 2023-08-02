package com.bobo.purview.component.config;

import com.bobo.purview.interceptors.PermissionInterceptor;
import com.bobo.purview.interceptors.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  @Autowired
  TokenInterceptor tokenInterceptor;

  @Autowired
  PermissionInterceptor permissionInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(tokenInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/user/login");

    registry.addInterceptor(permissionInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/user/login");
  }

}
