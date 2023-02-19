package com.bobo.study.chapter10.Inner_;

public class Inner01 {
  public static void main(String[] args) {
    new Outer("sadasi").inner();;
  }
}

class Outer {// 外部类
  private String name;
  {
    System.out.println("o1代码块");
  }

  public void o1() {

    System.out.println("o1方法");
  }

  public Outer(String name) {
    this.name = name;
  }

  static class Inner {// 成员内部类
    public void fuck() {
      Outer foo = new Outer("name");
      
      System.out.println(foo.name);
    }
  }

  public void inner() {
    Inner foo = new Inner();
    foo.fuck();
    
  }
}

class Other {// 其他类

  Object foo = new Outer.Inner();
  
}
