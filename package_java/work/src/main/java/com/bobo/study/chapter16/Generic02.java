package com.bobo.study.chapter16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;

public class Generic02 {
  public static void main(String[] args) {
    // 使用泛型
    ArrayList<Dog> arrayList = new ArrayList<Dog>();
    arrayList.add(new Dog("null", 0));
    arrayList.add(new Dog("null", 0));
    arrayList.add(new Dog("null", 0));
    arrayList.add(new Dog("null", 0));

    // 加入不小心加入了一只猫
    // 如果不满足要求就会报错
    // arrayList.add(new Cat("null", 0));

    for (Dog dog : arrayList) {
      dog.getName();
    }

    Persion<String> foo = new Persion<String>("null");

    HashMap<String,Cat> map = new HashMap<String,Cat>();

    Iterator<Entry<String,Cat>> iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<String,Cat> next = iterator.next();
      String key = next.getKey();
      Cat value = next.getValue();
    }
    
  }
}
//E数据类型是再定义Persion对象时指定，在编译期间就确定了类型
class Persion<E>{
  E s;
  public Persion(E s) {
    this.s = s;
  }
  public E f() {
    return s;
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