package com.bobo.utils.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

abstract public class CurrencyHandler implements InvocationHandler{
  public Object target;
  public Advice advice;

  public CurrencyHandler(Advice advice) {
    this.advice = advice;
  }

  abstract public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

  public void setTarget(Object target) {
    this.target = target;
  }

}
