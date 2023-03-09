package com.bobo.study.test;

import java.util.ArrayList;

import com.bobo.study.chapter08.Modifier01;

public class ModifierTest {
  public static void main(String[] args) {
    // Generic<Integer> genericInteger = new Generic<Integer>(123456);
    // Generic generic = new Generic("111111");
    // System.out.println(generic.getKey());
    // // 传入的实参类型需与泛型的类型参数类型相同，即为String.
    // Generic<String> genericString = new Generic<String>("江疏影");
    // System.out.println(genericInteger.getKey());
    // System.out.println(genericString.getKey());
    // ArrayList<String> list = new ArrayList();
    // list.add("a");
    // list.add(1);
    People p = new People();
    Class class1 = p.getClass();
    String name1 = class1.getName();

  }

  public static <M> void name(M m) {
    System.out.println(m);
  }
}

class People {

  private long id;
  private int age;
  private int sex;
  private String name;

}

class Generic<T> {

  // key这个成员变量的类型为T,T的类型由外部指定
  private T name;

  // 泛型构造方法形参key的类型也为T，T的类型由外部指定
  public Generic(T name) {
    this.name = name;
  }

  // 泛型方法getKey的返回值类型为T，T的类型由外部指定
  public T getName() {
    return name;
  }

}

interface fanxing<I, M> {
  void method(I i);
}

class faxingimp<I, M> implements fanxing<I, M> {
  @Override
  public void method(I i) {
    System.out.println(i);
  }

  public void method1(M i) {
    System.out.println(i);
  }
}
