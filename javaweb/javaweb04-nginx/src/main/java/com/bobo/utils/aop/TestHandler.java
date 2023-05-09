package com.bobo.utils.aop;

import java.lang.reflect.Method;

public class TestHandler extends CurrencyHandler {

  public TestHandler(Advice advice) {
    super(advice);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Object result = null;
    try {
      advice.before();
      result = advice.around(target, method, args);
      advice.after();
    } catch (Exception e) {
      advice.afterThrowing(e.toString());
      System.out.println("回滚了");
    } finally {
      advice.afterReturning();
    }
    return result;
  }

}
