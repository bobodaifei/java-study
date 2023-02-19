package com.bobo.study.chapter09;

public class Pupil {
  public String name;
  public int age;
  private double score;

  public void testing() {
    System.out.println("小学生" + this.name + "正在考试");
  }

  @Override
  public String toString() {
    return "Pupil [name=" + name + ", age=" + age + ", score=" + score + "]";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

}
