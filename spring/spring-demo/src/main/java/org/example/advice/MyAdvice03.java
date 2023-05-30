package org.example.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAdvice03 implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    System.out.println("环绕前");
    // 根据目标方法的引用 获取方法 调用invoke(对象，参数)
    Object res = invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());
    System.out.println("环绕后");
    return res;
  }




}
