package org.example.advice;

import org.springframework.stereotype.Component;

// @Component
public class MyAdvice {
  public void before() {
    System.out.println("before");
  }
  public void after() {
    System.out.println("after");
  }
}
