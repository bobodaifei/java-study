package com.bobo.study.chapter09;

public class Sub extends Base{

  public Sub(){
    //隐藏了super(); 调用了无参构造器
    super("null");
    System.out.println("Sub()....");
  }
  
  public Sub(String name) {
    super("name");
    System.out.println("Sub(name)....");
  }
  public void canSay() {
    System.out.println(n1);
    System.out.println(n2);
    System.out.println(n3);
    m1();
    m2();
    m3();
  }

}
