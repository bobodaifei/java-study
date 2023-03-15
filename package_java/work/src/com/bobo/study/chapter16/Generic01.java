package com.bobo.study.chapter16;

import java.util.ArrayList;

@SuppressWarnings({ "all" })
public class Generic01 {
  public static void main(String[] args) {
    // 传统方法
    ArrayList arrayList = new ArrayList();
    arrayList.add(new Dog("null", 0));
    arrayList.add(new Dog("null", 0));
    arrayList.add(new Dog("null", 0));
    arrayList.add(new Dog("null", 0));

    // 加入不小心加入了一只猫
    arrayList.add(new Cat("null", 0));

    for (Object object : arrayList) {
      Dog dog = (Dog) object;
      dog.getName();
    }

  }
}

class Cat {
  private String name;
  private int age;

  public Cat(String name, int age) {
    this.name = name;
    this.age = age;
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

}

class Dog {
  private String name;
  private int age;

  public Dog(String name, int age) {
    this.name = name;
    this.age = age;
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

}