package com.bobo.init;

import com.bobo.config.SpringMVCConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

//巧妙方式调用SpringMVC配置类
public class MyAnnotationMVCConfigWebApplicationContext extends AnnotationConfigWebApplicationContext {

  public MyAnnotationMVCConfigWebApplicationContext() {
    super.register(SpringMVCConfig.class);
  }

}
