package com.bobo.study.chapter09.homework;

public class HomeWork01 {
  public static void main(String[] args) {
    Persion p1 = new Persion("zhang1", 12, "汽修");
    Persion p2 = new Persion("zhang2", 2, "汽修");
    Persion p3 = new Persion("zhang3", 36, "汽修");
    Persion p4 = new Persion("zhang4", 356, "汽修");
    Persion p5 = new Persion("zhang5", 5566, "汽修");
    Persion[] parr = new Persion[5];
    parr[0] = p1;
    parr[1] = p2;
    parr[2] = p3;
    parr[3] = p4;
    parr[4] = p5;
    boolean over = true;
    for (int i = 0; i < parr.length - 1; i++) {
      over = true;
      for (int j = 0; j < parr.length - i - 1; j++) {
        if (parr[j].getAge() < parr[j + 1].getAge()) {
          Persion p = parr[j];
          parr[j] = parr[j + 1];
          parr[j + 1] = p;
          over = false;
        }
      }
      if (over) {
        break;
      }
    }
    for (int i = 0; i < parr.length; i++) {
      System.out.println(parr[i].getAge());
    }
  }
}

class Persion {
  private String name;
  private int age;
  private String job;

  public Persion(String name, int age, String job) {
    this.name = name;
    this.age = age;
    this.job = job;
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

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

}
