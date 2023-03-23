package com.bobo.study.chapter09.object_;

public class Equal01 {
  public static void main(String[] args) {
    A a = new A();
    A b = a;
    A c = b;
    System.out.println(a==b);
    System.out.println(a==c);

    B ob = a;
    System.out.println(a == ob);
    System.out.println("hewll".equals("ob"));
    Integer integer1 = new Integer(1000);
    Integer integer2 = new Integer(1000);

    System.out.println(integer1 == integer2);
    System.out.println(integer1.equals(integer2));
  }
}
class A extends B{

}
class B{

}
