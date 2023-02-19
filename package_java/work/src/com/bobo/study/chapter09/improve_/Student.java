package com.bobo.study.chapter09.improve_;

public class Student {
  public String name;
  public int age;
  private double score;
  
  public String showInfo() {
    return "Pupil [name=" + name + ", age=" + age + ", score=" + score + "]";
  }

  public void setScore(double score) {
    this.score = score;
  }
}
