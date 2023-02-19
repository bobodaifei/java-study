package com.bobo.study.chapter10.Inner_;

public class LocalInner01 {
  public static void main(String[] args) {
    // Outer_Local foo = new Outer_Local("1");
    // foo.o11();
    new Other01("null").method();
  }
}

class Outer_Local {// 外部类
  private String name = "1";

  {
    System.out.println("o1代码块");
  }

  public void o11() {
    class Inner {// 内部类
      private String name = "2";

      public void o1() {
        System.out.println(this.name);
      }
    }
    System.out.println("o1方法");
    Inner foo = new Inner();
    foo.o1();
  }

  public Outer_Local(String name) {
    this.name = name;
  }

}

class Other01 {// 其他类
  private String name = "1";

  public Other01(String name) {
    this.name = name;
  }

  public void method() {
    IA ia = new IA() {
      @Override
      public void cry() {
        // TODO Auto-generated method stub
        System.out.println("aoaojiao");
      }
    };
    ia.cry();
    System.out.println(ia.getClass());
  }

  public void method1() {
    // IB ib = new IB("fuck name");
    IB ib1 = new IB(" "){

      @Override
      public void cry() {
        // TODO Auto-generated method stub
        super.cry();
      }
      
    };
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}

class IB {
  private String name;

  public IB(String name) {
    this.name = name;
  }

  public void cry() {
    System.out.println("fuck");
  };
}

interface IA {
  public void cry();
}
