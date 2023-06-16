package com.bobo.advice;

import com.bobo.utils.JdbcUtil;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class MyAdvice {

  @Pointcut("execution(* com.bobo.service.impl.*.update*(..))")
  public void update() {
  }

  @Pointcut("execution(* com.bobo.service.impl.*.delete*(..))")
  public void delete() {
  }

  @Pointcut("execution(* com.bobo.service.impl.*.insert*(..))")
  public void insert() {
  }

  @Pointcut("execution(* com.bobo.service.impl.*.login(..))")
  public void login() {
  }


  @Before("MyAdvice.update()||MyAdvice.delete()||MyAdvice.insert()||MyAdvice.login()")
  public void before() {
    System.out.println("before");
    JdbcUtil.begin();
  }

  @After("MyAdvice.update()||MyAdvice.delete()||MyAdvice.insert()||MyAdvice.login()")
  public void after() {
    System.out.println("after");
  }

  @AfterThrowing(value = "MyAdvice.update()||MyAdvice.delete()||MyAdvice.insert()||MyAdvice.login()", throwing = "e")
  public void throwing(Throwable e) {
    System.out.println(e);
    JdbcUtil.rollback();
    JdbcUtil.close();
  }

  @AfterReturning("MyAdvice.update()||MyAdvice.delete()||MyAdvice.insert()||MyAdvice.login()")
  public void afterReturning() {
    System.out.println("afterReturning");
    JdbcUtil.commit();
    JdbcUtil.close();
  }
}
