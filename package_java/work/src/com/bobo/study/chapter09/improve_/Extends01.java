package com.bobo.study.chapter09.improve_;

public class Extends01 {
  public static void main(String[] args) {
    Pupil pupil = new Pupil();
    pupil.name = "金角";
    pupil.age = 15;
    pupil.testing();
    pupil.setScore(120);
    System.out.println(pupil.showInfo());

    Graduate graduate = new Graduate();
    graduate.name = "金角";
    graduate.age = 15;
    graduate.testing();
    graduate.setScore(120);
    System.out.println(graduate.showInfo());
  }

}

