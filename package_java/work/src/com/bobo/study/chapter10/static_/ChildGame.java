package com.bobo.study.chapter10.static_;

public class ChildGame {
  public static void main(String[] args) {
    Child foo = new Child("sad");
    Child foo1 = new Child("sad");
    Child foo2 = new Child("sad");
    Child.name();
    System.out.println(foo.getNum());
    foo2.name();
    System.out.println(Child.getNum());

  }
}
class Child{
  private String name;
  private static int num=0;
  public Child(String name) {
    this.name = name;
    this.num++;
  }

  public static void name() {
    
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public static int getNum() {
    return num;
  }
  public static void setNum(int num) {
    Child.num = num;
  }
  
  
}
