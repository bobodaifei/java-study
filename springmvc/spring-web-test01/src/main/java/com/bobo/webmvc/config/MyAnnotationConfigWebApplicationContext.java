package com.bobo.webmvc.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

//巧妙方式调用SpringMVC配置类
public class MyAnnotationConfigWebApplicationContext extends AnnotationConfigWebApplicationContext {

  public MyAnnotationConfigWebApplicationContext() {
    super.register(SpringMVCConfig.class);
  }

}
