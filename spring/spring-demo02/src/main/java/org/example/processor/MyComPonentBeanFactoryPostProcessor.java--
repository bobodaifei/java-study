package org.example.processor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.example.utils.BaseClassScanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * MyComPonentBeanFactoryPostProcessor
 */
public class MyComPonentBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
  }

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    // 通过扫描工具取指定包及其包下的所有类，收集使用@MyComponent的类
    Map<String, Class> map = BaseClassScanUtils.scanMyComponentAnnotation("org.example");
    map.forEach((beanName, clazz) -> {
      String beanClassName = clazz.getName();
      BeanDefinition beanDefinition = new RootBeanDefinition();
      beanDefinition.setBeanClassName(beanClassName);
      registry.registerBeanDefinition(beanName, beanDefinition);
      System.out.println("在实例化前注解注册了" + beanName);
    });

  }
}