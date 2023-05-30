package org.example.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

public class MyAdvice02 implements MethodBeforeAdvice, AfterReturningAdvice {

  @Override
  public void afterReturning(@Nullable Object returnValue, Method method, Object[] args, @Nullable Object target)
      throws Throwable {
    System.out.println("后置");
  }

  @Override
  public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
    System.out.println("前置");
  }

}
