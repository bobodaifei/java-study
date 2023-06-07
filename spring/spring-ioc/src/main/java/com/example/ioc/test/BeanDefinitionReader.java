package com.example.ioc.test;

public interface BeanDefinitionReader {

  BeanDefinitionRegistry getRegistry();

  void loadBeanDefinitions(String configLocation) throws Exception;
}
