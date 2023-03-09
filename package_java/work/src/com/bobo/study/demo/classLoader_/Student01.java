package com.bobo.study.demo.classLoader_;

public class Student01 {

  public String name;
  private int name1;

  public Student01() {
    System.out.println(" construct");
  }
  

  public Student01(String name, int name1) {
    System.out.println(" construct111");
  }


  static{
    System.out.println(" static");
  }

  @Override
  public String toString() {
    return "Student01 []"+name;
  }
  public void toString(String str) {
    System.out.println(str);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  
  
}
