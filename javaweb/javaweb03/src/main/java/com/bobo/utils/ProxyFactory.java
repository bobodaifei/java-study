package com.bobo.utils;

import java.lang.reflect.Proxy;

public class ProxyFactory {

  public static Object getInstance(Class class_, CurrencyHandler handler) {
    Object newInstance = null;
    try {
      newInstance = class_.getConstructor().newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    handler.setTarget(newInstance);
    return Proxy.newProxyInstance(class_.getClassLoader(), class_.getInterfaces(), handler);
  }

}
