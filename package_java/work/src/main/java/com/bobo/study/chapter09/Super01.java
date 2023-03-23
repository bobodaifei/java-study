package com.bobo.study.chapter09;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Super01 {
  public static void main(String[] args) {
    // B.n1();
    // C.n1();
    Date date = new Date();
    System.out.println(date.getHours());
    System.out.println(date);
    System.out.println(date);

    System.out.println(LocalDate.now().getMonth());
    // System.out.println(LocalDateTime.now().start);

    // Calendar.YEAR;
    System.out.println(Calendar.getInstance());

  }
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

  public static void n1() {
    System.out.println("fuqin");
  }

}

class C extends B {

  @Override
  public void name() {
    System.out.println(super.n1);
    super.name1();
  }

  @Override
  public void name1() {
    // TODO Auto-generated method stub
    System.out.println("zi1");
  }

}

class Persion {
  public void sleep() {
    System.out.println("11点睡觉");
  }
}

class Student extends Persion {

  @Override
  public void sleep() {
    // TODO Auto-generated method stub
    System.out.println("我就要12点睡觉");
  }

}