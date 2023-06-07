package com.example.ioc.test;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractApplicationContext implements ApplicationContext {
  //解析器(包含了解析xml、封装bean对象)
  protected BeanDefinitionReader beanDefinitionReader;

  //bean对象map
  protected Map<String, Object> singletonObject = new ConcurrentHashMap<>();

  //配置文件的路径
  protected String configLocation;

  public void setConfigLocations(String configLocation) {
    this.configLocation = resolvePath(configLocation);
  }

  protected String resolvePath(String path) {
    URL url = ApplicationContext.class.getClassLoader().getResource(path);
    if (url == null) {
      throw new RuntimeException("路径不存在");
    }
    return url.getPath();
  }

  @Override
  public void refresh() throws Exception {
    //封装BeanDefinition对象
    beanDefinitionReader.loadBeanDefinitions(configLocation);
    //初始化bean
    finishBeanInitialization();
  }

  private void finishBeanInitialization() throws Exception{
    BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
    String[] beanNames = registry.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      getBean(beanName);
    }
  }


}
