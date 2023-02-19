package com.bobo.study.chapter09.objectpoly;

public class PolyObject {
  public static void main(String[] args) {
    Cat cat = new Cat();
    cat.setName("小花");
    Master master = new Master();
    master.name="tom";
    // master.play(cat);

    Animal animal = new Cat();
    System.out.println(animal.getName());
    System.out.println(animal.name);
    System.out.println(cat.getName());

    Cat cat1 = (Cat) animal;
    // cat1.wc();
    // cat1.aaa();
  }
}
