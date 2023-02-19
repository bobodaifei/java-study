package com.bobo.study.chapter09;

public class Base {
  public int n1 = 100;
  protected int n2 = 200;
  int n3 = 300;
  private int n4 = 400;

  // public Base() {
  //   System.out.println("base()...");
  // }
  
  public Base(String name) {
    System.out.println("base(name)...");
  }

  public void m1() {
    System.out.println("test100");
  }

  protected void m2() {
    System.out.println("test200");
  }

  void m3() {
    System.out.println("test300");
  }

  private void m4() {
    System.out.println("test400");
  }
}
