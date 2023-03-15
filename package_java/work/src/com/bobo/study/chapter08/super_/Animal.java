package com.bobo.study.chapter08.super_;

public abstract class Animal {
  int age;
  String gender;
  String color;

  public Animal(int age, String gender, String color) {
    this.age = age;
    this.gender = gender;
    this.color = color;
  }

  public abstract void eat();


  public String drink(){
    return "喝水";
  };
}
