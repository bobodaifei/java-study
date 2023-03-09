package com.bobo.study.demo.work01;

public class MethodUtils {
  public static void main(String[] args) {

    // System.out.println(max(561, 85, 2));
    // allDivisor(27);
    // helloWord(3);
    // numberDigits(2515);
    // isdivide3();
    // isPrimeNum(12);
    // divide7Total();
    // System.out.println(absoluteValue(-1.5));
    // System.out.println(halfAdjust(1.49999999999));
  }

  private static int max(int num1, int num2, int num3) {
    int max;
    return (max = (num1 > num2 ? num1 : num2)) > num3 ? max : num3;
  }

  public static void allDivisor(int num) {
    for (int i = 1; i < num >> 2; i++) {
      if (num % i == 0) {
        System.out.println(i);
      }
    }
  }

  public static void helloWord(int n) {
    for (int i = 0; i < n; i++) {
      System.out.println("helloWord");
    }
  }

  public static void numberDigits(int n) {
    System.out.println((n + "").length());
  }

  public static void isdivide3() {
    for (int i = 100; i <= 200; i++) {
      if (i % 3 == 0) {
        System.out.println(i);
      }
    }
  }

  public static void isPrimeNum(int n) {
    for (int i = 2; i < n >> 2; i++) {
      if (n % i == 0) {
        System.out.println("不是素数");
        return;
      }
    }
    System.out.println("是素数");
  }

  public static void divide7Total() {
    int total = 0;
    for (int i = 1; i <= 100; i++) {
      if (i % 7 == 0) {
        total++;
      }
    }
    System.out.println(total);
  }

  public static double absoluteValue(double d) {
    return d > 0 ? d : 0 - d;
  }

  public static int halfAdjust(double d) {
    String str = d + "";
    String[] arr = str.split("\\.");
    double d1 = Double.parseDouble("0." + arr[1]);
    System.out.println(d1);
    if (d1 >= 0.5) {
      return (int) d + 1;
    }
    return (int) d;
  }
}
