package com.bobo.study.chapter12.exception_;

public class Exception01 {
  public static void main(String[] args) {
    try {
      System.out.println("15");
    } catch (Exception e) {
      // TODO: handle exception
      // e.printStackTrace();
      System.out.println(e);
    }
    System.out.println("继续运行");
    try {

    } catch (Exception e) {
      // TODO: handle exception
    }
    // Throwable
  }

  public static void name() {
    System.out.println(1 / 0);

  }
}
