package com.example.ioc.test;

public interface BeanFactory {

  Object getBean(String name) throws Exception;

  <T> T getBean(String name, Class<? extends T> type) throws Exception;

}
