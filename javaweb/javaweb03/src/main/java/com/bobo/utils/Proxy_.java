package com.bobo.utils;

import java.lang.reflect.Method;

public class Proxy_<T> {
  private T proxy;
  private Method method;

  public Proxy_(Class<T> class_, String method, Class<?>... parameterTypes) {
    try {
      this.proxy = class_.getConstructor().newInstance();
      this.method = class_.getMethod(method, parameterTypes);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object newProxyInstance(Object... args) {
    Object result = null;
    try {
      JdbcUtil.begin();
      result = method.invoke(proxy, args);
      JdbcUtil.commit();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        //回滚了
        System.out.println("回滚了");
        JdbcUtil.rollback();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } finally {
      try {
        JdbcUtil.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    };
    return result;
  }
}
