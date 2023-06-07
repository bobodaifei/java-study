package com.example.ioc.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeanDefinitionRegistry implements BeanDefinitionRegistry {
  private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

  @Override
  public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
    beanDefinitionMap.put(beanName, beanDefinition);
  }

  @Override
  public void removeBeanDefinition(String beanName) {
    beanDefinitionMap.remove(beanName);
  }

  @Override
  public BeanDefinition getBeanDefinition(String beanName) {
    return beanDefinitionMap.get(beanName);
  }

  @Override
  public boolean containBeanDefinition(String beanName) {
    return beanDefinitionMap.containsKey(beanName);
  }

  @Override
  public String[] getBeanDefinitionNames() {
    return beanDefinitionMap.keySet().toArray(new String[0]);
  }
}
