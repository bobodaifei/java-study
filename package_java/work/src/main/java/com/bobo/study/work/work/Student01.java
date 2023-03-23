package com.bobo.study.work.work;

public class Student01 {

  public String name;

  public Student01() {
    System.out.println(" construct");
  }

  public Student01(String name, int name1) {
    System.out.println(" construct111");
  }

  static {
    System.out.println(" static");
  }

  @Override
  public String toString() {
    return "Student01 []" + name;
  }

  public void toString(String str) {
    System.out.println(str);
  }

  public Object toString1(Object[]  str) {
    return str[0];
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
