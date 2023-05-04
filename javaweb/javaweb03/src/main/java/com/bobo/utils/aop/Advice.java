package com.bobo.utils.aop;

import java.lang.reflect.Method;

public interface Advice {
  // 执行前切入
  public void before();

  // 执行后切入
  public void after();

  public void afterThrowing(String message);

  public void afterReturning();

  public Object around(Object target, Method method, Object[] args) throws Throwable;
}
