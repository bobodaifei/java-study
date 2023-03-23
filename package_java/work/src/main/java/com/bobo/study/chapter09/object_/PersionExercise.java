package com.bobo.study.chapter09.object_;

public class PersionExercise {
  public static void main(String[] args) {
    Persion p1 = new Persion("bobo",10,'男');
    Persion p2 = new Persion("bobo",101,'男');
    // Persion p2 = new Persion("bibi", 12, '女');
    System.out.println(p1.equals(p2));
  }
}

class Persion {
  private String name;
  private int age;
  private char gender;
  
  public Persion() {
  }

  public Persion(String name, int age, char gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Persion) {
      Persion p = (Persion)obj;
      return this.getName().equals(p.getName())&&this.getAge()==p.getAge()&&this.getGender()==p.getGender();
    }
    return false;

  }
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public char getGender() {
    return gender;
  }
  public void setGender(char gender) {
    this.gender = gender;
  }
  
}
