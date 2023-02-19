package com.bobo.study.chapter09.objectpoly;

public class Master {
  String name;
  public void play(Animal animal) {
    System.out.println(this.name+"主人和"+ animal.getName()+"玩");
  }
}
