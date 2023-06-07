package com.example.ioc.test;

public interface BeanDefinitionRegistry {

  void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

  void removeBeanDefinition(String beanName);

  BeanDefinition getBeanDefinition(String beanName);

  boolean containBeanDefinition(String beanName);

  String[] getBeanDefinitionNames();
}
