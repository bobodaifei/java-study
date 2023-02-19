package com.bobo.study.chapter09;

public class Super01 {

}

class A {
  public int n1 = 100;
  protected int n2 = 200;
  int n3 = 300;
  private int n4 = 400;

  public void name1() {
    System.out.println();
  }
}

class B extends A {

  public void name() {
    System.out.println(super.n1);
  }

}

class C extends B {

  @Override
  public void name() {
    System.out.println(super.n1);
    super.name1();
  }

}