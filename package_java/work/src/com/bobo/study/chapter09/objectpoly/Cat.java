package com.bobo.study.chapter09.objectpoly;

public class Cat extends Animal{
  public String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public void cry() {
    System.out.println("Cat 喵喵叫");
  }
  
  public void wc() {
    System.out.println("cat wc");
  }
}
