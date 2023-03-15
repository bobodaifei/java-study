package com.bobo.study.chapter08.super_;

public class Cat extends Animal {

  public Cat(int age, String gender, String color) {
    super(age, gender, color);
  }

  @Override
  public void eat() {
    System.out.println("猫吃肉");
  }

  public void show() {
    System.out.println(super.age + "岁的" + super.gender + "" + super.color + "猫");
  }

  @Override
  public String drink() {
    return "猫"+super.drink();
  }

  public static void main(String[] args) {
    Cat cat = new Cat(12, "公", "黑");
    cat.show();
    cat.eat();
    System.out.println(cat.drink());
    

    Dog dog = new Dog(10, "母", "蓝");
    dog.show();
    dog.eat();
    System.out.println(dog.drink());
    

  }

}
