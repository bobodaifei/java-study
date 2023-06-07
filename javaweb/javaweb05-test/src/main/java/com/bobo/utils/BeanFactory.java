package com.bobo.utils;

import com.bobo.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanFactory {
  static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

  public static <T> T getBean(Class<T> clazz){
    return applicationContext.getBean(clazz);
  }

}
