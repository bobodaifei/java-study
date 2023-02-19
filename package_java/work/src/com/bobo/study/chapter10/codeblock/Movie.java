package com.bobo.study.chapter10.codeblock;

public class Movie {
  private String name;
  private double price;
  private static double a1 = a1();
  private double a2 = a2();

  //当我们创建对象后会调用代码块
  static {
    System.out.println("static1");
  }

  {
    System.out.println("打开1");
  }

  public static double a1() {
    System.out.println("a1");
    return 100;
  }
  public double a2() {
    System.out.println("a2");
    return 100;
  }
  

  public Movie(String name) {
    this.name = name;
  }
  public Movie(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public static void ads() {
    System.out.println("ads");
  }
}
class Movie1 extends Movie{

  static {
    System.out.println("static2");
  }
  {
    System.out.println("打开2");
  }
  
  public Movie1(String name) {
    super(name);
    //TODO Auto-generated constructor stub
  }

}