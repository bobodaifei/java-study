package com.bobo.study.work.work03;

public class Animal {
  
  String name;
  int age;

  public void eat() {
    
  }
}

class Bird extends Animal{

  @Override
  public void eat() {
    System.out.println("我吃虫");
  }

  public void fly() {
    System.out.println("我能飞");
  }
  
}

class Fish extends Animal{
  @Override
  public void eat() {
    System.out.println("我吃鱼");
  }

  public void swim() {
    System.out.println("我会游泳");
  }
}

class Cxk extends Bird{

  @Override
  public void fly() {
    System.out.println("我不会飞");
  }

  public void cry() {
    System.out.println("哎呦，你干嘛！");
  }
  
}