package com.bobo.study.chapter12.exception_;

public class CustomException {
  public static void main(String[] args) {
    int age = 10;
    if (!(age > 50)) {
      throw new AgeException("年龄必须大于50");
    }
  }

  
}

class AgeException extends RuntimeException {

  // 自定义的异常
  public AgeException(String message) {
    super(message);
  }

}
