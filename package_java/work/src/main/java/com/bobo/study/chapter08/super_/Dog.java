package com.bobo.study.chapter08.super_;

public class Dog extends Animal {

  public Dog(int age, String gender, String color) {
    super(age, gender, color);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void eat() {
    System.out.println("狗吃骨头");
  }

  public void show() {
    System.out.println(super.age + "岁的" + super.gender + "" + super.color + "狗");
  }

  @Override
  public String drink() {
    return "狗" + super.drink();
  }

}
