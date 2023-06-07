package com.example.ioc.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanDefinition {
  private String beanName;

  private String beanClass;

  private Map<String, String> properties = new ConcurrentHashMap<>();

  private Map<String, Object> constructorargs = new ConcurrentHashMap<>();

  public String getBeanName() {
    return beanName;
  }

  public void setBeanName(String beanName) {
    this.beanName = beanName;
  }

  public String getBeanClass() {
    return beanClass;
  }

  public void setBeanClass(String beanClass) {
    this.beanClass = beanClass;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(String key, String value) {
    this.properties.put(key, value);
  }

  public Object getConstructorargs(String key) {
    return constructorargs.get(key);
  }

  public void setConstructorargs(String key, Object value) {
    this.constructorargs.put(key, value);
  }
}
