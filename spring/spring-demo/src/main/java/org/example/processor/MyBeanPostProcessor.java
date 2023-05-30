package org.example.processor;

import java.lang.reflect.Proxy;

import org.example.dao.MyComponent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

public class MyBeanPostProcessor implements BeanPostProcessor {

  @Override
  @Nullable
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    Object beanProxy = Proxy.newProxyInstance(
        bean.getClass().getClassLoader(),
        bean.getClass().getInterfaces(),
        (proxy, method, args) -> {
          System.out.println(beanName + "开始时间");
          Object result = method.invoke(bean, args);
          System.out.println(beanName + "结束时间");
          return result;
        });

    return beanProxy;
  }

  @Override
  @Nullable
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    // TODO Auto-generated method stub
    return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }

}
