package com.example.cloud.component.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

//  @Autowired
//  TokenInterceptor tokenInterceptor;
//
//  @Autowired
//  PermissionInterceptor permissionInterceptor;
//
//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(tokenInterceptor)
//            .addPathPatterns("/**")
//            .excludePathPatterns("/user/login");
//
//    registry.addInterceptor(permissionInterceptor)
//            .addPathPatterns("/**")
//            .excludePathPatterns("/user/login");
//  }

//    @Bean
////    @LoadBalanced
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

}
