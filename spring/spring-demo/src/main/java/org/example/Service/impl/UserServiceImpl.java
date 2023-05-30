package org.example.service.impl;

import javax.servlet.ServletContext;

import org.example.dao.MyComponent;
import org.example.dao.UserDao;
import org.example.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

// @MyComponent("userService")
public class UserServiceImpl
    implements UserService, ServletContextAware, ApplicationContextAware, BeanFactoryAware, BeanNameAware {
  // BeanFactory去调用此方法 从容器中获得userDao并设置到此处
  public void setUserDao(UserDao UserDao) {
    System.out.println("userDao被设置了");
  }

  @Override
  public int name() throws InterruptedException {
    Thread.sleep(1000);
    return 1;
  }

  @Override
  public void setServletContext(ServletContext servletContext) {
    System.out.println(servletContext);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    System.out.println(applicationContext);
  }

  @Override
  public void setBeanName(String name) {
    System.out.println(name);
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    System.out.println(beanFactory);
  }

}
