package com.bobo.study.chapter09;

public class DynamicBinding {
  public static void main(String[] args) {
    AA a = new BB();
    System.out.println(a.sum());
    System.out.println(a.sum1());
    System.out.println(a.i1);

  }
}

class AA {
  public int i = 10;
  public int i1 = 10;

  public int sum() {
    return get1() + 10;
  }

  public int sum1() {
    return i + 10;
  }

  public int get1() {
    return i;
  }
}

class BB extends AA {
  int i = 20;

  // public int sum() {
  //   return i + 20;
  // }

  public int get1() {
    return i;
  }

  public int sum1() {
    return i + 10;
  }
}
