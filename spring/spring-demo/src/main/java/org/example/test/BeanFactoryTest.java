package org.example.test;

import org.example.service.UserService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanFactoryTest {
  public static void main(String[] args) {
    //创建工厂对象
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    //创建一个读取器（xml）
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    //读取器和绑定工厂对象
     reader.loadBeanDefinitions("beans.xml");
    //根据id获取bean对象
    UserService userService = (UserService)beanFactory.getBean("userService");
    System.out.println(userService);
  }
}
