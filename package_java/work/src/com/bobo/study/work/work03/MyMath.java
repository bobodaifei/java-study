package com.bobo.study.work.work03;

public class MyMath {

  public void compute() {

  }

  public static void main(String[] args) {
    Sum sum = new Sum();
    sum.compute(1, 10);
  }
}

class Sum extends MyMath {

  public void compute(int i, int j) {
    System.out.println(i + j);
  }

  public void compute(Double i, Double j) {
    System.out.println(i + j);
  }

  public void compute(Float i, Float j) {
    System.out.println(i + j);
  }

  public void compute(Long i, Long j) {
    System.out.println(i + j);
  }
}

class Subtract extends MyMath {
  public void compute(int i, int j) {
    System.out.println(i - j);
  }

  public void compute(Double i, Double j) {
    System.out.println(i - j);
  }

  public void compute(Float i, Float j) {
    System.out.println(i - j);
  }

  public void compute(Long i, Long j) {
    System.out.println(i - j);
  }
}

class Mult extends MyMath {
  public void compute(int i, int j) {
    System.out.println(i * j);
  }

  public void compute(Double i, Double j) {
    System.out.println(i * j);
  }

  public void compute(Float i, Float j) {
    System.out.println(i * j);
  }

  public void compute(Long i, Long j) {
    System.out.println(i * j);
  }
}

class Division extends MyMath {
  public void compute(int i, int j) {
    System.out.println(i / j);
  }

  public void compute(Double i, Double j) {
    System.out.println(i / j);
  }

  public void compute(Float i, Float j) {
    System.out.println(i / j);
  }

  public void compute(Long i, Long j) {
    System.out.println(i / j);
  }
}