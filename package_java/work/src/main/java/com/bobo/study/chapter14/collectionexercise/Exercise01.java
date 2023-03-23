package com.bobo.study.chapter14.collectionexercise;

import java.util.ArrayList;
import java.util.Iterator;

public class Exercise01 {
  public static void main(String[] args) {
    ArrayList list = new ArrayList();
    list.add(new Dog("狗子1", 2));
    list.add(new Dog("狗子2", 4));
    list.add(new Dog("狗子3", 5));
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      System.out.println(object.toString());
    }
    for (Object object : list) {
      System.out.println(object.toString());
    }
  }
}

class Dog{
  private String name;
  private int age;
  public Dog(String name, int age) {
    this.name = name;
    this.age = age;
  }
  @Override
  public String toString() {
    return "Dog [name=" + name + ", age=" + age + "]";
  }
  
}