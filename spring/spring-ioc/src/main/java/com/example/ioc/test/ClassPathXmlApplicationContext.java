package com.example.ioc.test;

import com.example.ioc.service.UserService;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

  public ClassPathXmlApplicationContext(String configLocation) throws Exception {
    setConfigLocations(configLocation);
    beanDefinitionReader = new XmlBeanDefinitionReader();
    this.refresh();
  }


  @Override
  public Object getBean(String beanName) throws Exception {
    Object object = null;
    if ((object = singletonObject.get(beanName)) != null) {
      return object;
    }
    BeanDefinitionRegistry beanDefinitionRegistry = beanDefinitionReader.getRegistry();
    BeanDefinition beanDefinition = null;
    if ((beanDefinition = beanDefinitionRegistry.getBeanDefinition(beanName)) == null) {
      return "找不到";
    }
    String beanClass = beanDefinition.getBeanClass();

    //实例化
    Class<?> clazz = Class.forName(beanClass);
    Object beanObject = clazz.getConstructor().newInstance();

    //属性注入
    Map<String, String> properties = beanDefinition.getProperties();
    Iterator<Map.Entry<String, String>> iterator = properties.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, String> next = iterator.next();
      String name = next.getKey();
      String value = next.getValue();
      String setterName = StringUtils.getSetterName(name);
      Method method = clazz.getMethod(setterName, String.class);
      method.invoke(beanObject, value);
    }
    singletonObject.put(beanName, beanObject);

    return beanObject;
  }

  @Override
  public <T> T getBean(String name, Class<? extends T> type) throws Exception {
    Object bean = getBean(name);
    if (bean == null) {
      return null;
    }
    return type.cast(bean);
  }

  public static void main(String[] args) throws Exception {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    UserService userService = applicationContext.getBean("userService", UserService.class);
    System.out.println(userService);
  }
}
