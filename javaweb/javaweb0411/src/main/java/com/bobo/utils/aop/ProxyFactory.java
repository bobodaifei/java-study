package com.bobo.utils.aop;

import java.lang.reflect.Proxy;

public class ProxyFactory {

  public static Object getInstance(Class class_, CurrencyHandler handler) {
    Object target = null;
    try {
      target = class_.getConstructor().newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    handler.setTarget(target);
    return Proxy.newProxyInstance(class_.getClassLoader(), class_.getInterfaces(), handler);
  }

}
