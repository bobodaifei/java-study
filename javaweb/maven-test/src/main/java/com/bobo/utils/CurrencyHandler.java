package com.bobo.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

abstract public class CurrencyHandler implements InvocationHandler{
  public Object target;

  abstract public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

  public void setTarget(Object target) {
    this.target = target;
  }
}
