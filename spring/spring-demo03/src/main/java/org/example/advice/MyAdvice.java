package org.example.advice;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


// @Component
// @Aspect
public class MyAdvice {

  @Pointcut("execution(void org.example.service.impl.ClassServiceImpl.show1())")
  public void myCut() {
  }

  @Before("MyAdvice.myCut()")
  public void before() {
    System.out.println("before");
  }

  @After("execution(void org.example.service.impl.ClassServiceImpl.show1())")
  public void after() {
    System.out.println("after");
  }

  @AfterThrowing(pointcut = "execution(void org.example.service.impl.ClassServiceImpl.show1())",throwing = "e")
  public void throwing(Throwable e) {
    System.out.println(e);
    System.out.println("after");
  }
}
