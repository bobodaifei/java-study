package com.example.ioc.test;

public interface ApplicationContext extends BeanFactory {
  void refresh() throws Exception;
}
