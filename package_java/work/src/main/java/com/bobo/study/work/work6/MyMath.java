package com.bobo.study.work.work6;

public class MyMath {
  public static void main(String[] args) {
    System.out.println(new Sum01().compete(12, 13.2));
    
  }

  public void compete() {

  }
}

class Sum01 extends MyMath {

  public static double compete(double num1, double num2) {
    double num = num1 + num2;
    return num;
  }

}

class Subt01 extends MyMath {
  public static double compete(double num1, double num2) {
    double num = num1 - num2;
    return num;
  }
}

class Mult01 extends MyMath {
  public static double compete(double num1, double num2) {
    double num = num1 * num2;
    return num;
  }
}

class Division01 extends MyMath {
  public static double compete(double num1, double num2) {
    if (num2 == 0) {
      System.out.println("数据异常");
      throw new RuntimeException();
    }
    double num = num1 / num2;
    return num;
  }
}