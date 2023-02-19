package com.bobo.study.chapter08;

public class Modifier01 {
  public int n1 = 100;
  protected int n2 = 200;
  int n3 = 300;
  private int n4 = 400;

  public void m1() {
    System.out.println(n1 + n2 + n3 + n4);
  }
  
  protected void m2() {
    System.out.println(n1 + n2 + n3 + n4);
  }
  
  void m3() {
    System.out.println(n1 + n2 + n3 + n4);
  }
  
  private void m4() {
    System.out.println(n1 + n2 + n3 + n4);
  }
}
