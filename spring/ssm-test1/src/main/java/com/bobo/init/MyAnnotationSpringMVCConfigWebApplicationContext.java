package com.bobo.init;

import com.bobo.config.SpringConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

//巧妙方式调用SpringMVC配置类
public class MyAnnotationSpringMVCConfigWebApplicationContext extends AnnotationConfigWebApplicationContext {

  public MyAnnotationSpringMVCConfigWebApplicationContext() {
    super.register(SpringConfig.class);
  }

}
