package com.bobo.utils;

import java.lang.reflect.Method;

public class AffairsHandler extends CurrencyHandler {

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Object result = null;
    try {
      JdbcUtil.begin();
      result = method.invoke(target, args);
      JdbcUtil.commit();
    } catch (Exception e) {
      JdbcUtil.rollback();
      System.out.println("回滚了");
    } finally{
      JdbcUtil.close();
    }
    return result;
  }
  
}
