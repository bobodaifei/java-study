package org.example.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

public class MyBeanPostProcessor1 implements BeanPostProcessor {

  @Override
  @Nullable
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//    Object beanProxy = Proxy.newProxyInstance(
//        bean.getClass().getClassLoader(),
//        bean.getClass().getInterfaces(),
//        (proxy, method, args) -> {
//          System.out.println(beanName + "开始时间");
//          Object result = method.invoke(bean, args);
//          System.out.println(beanName + "结束时间");
//          return result;
//        });
      System.out.println("3333333333333333");
//    return beanProxy;
      return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }

  @Override
  @Nullable
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
      System.out.println("4444444444444444444");
    // TODO Auto-generated method stub
    return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }

}
