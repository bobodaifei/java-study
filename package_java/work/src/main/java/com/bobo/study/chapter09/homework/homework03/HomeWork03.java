package com.bobo.study.chapter09.homework.homework03;

public class HomeWork03 {
  public static void main(String[] args) {
    Teacher t1 = new Teacher("张老师1", '男', 20, 2);
    Teacher t2 = new Teacher("张老师2", '男', 0, 2);
    Student s1 = new Student("学生1", '女', 15, 425432);
    Student s2 = new Student("学生2", '女', 16, 425432);
    t1.teach();
    s1.study();
    System.out.println(t1.play());
    System.out.println(s1.play());

    Persion[] p = new Persion[4];
    p[0] = t1;
    p[1] = t2;
    p[2] = s1;
    p[3] = s2;
    HomeWork03 foo = new HomeWork03();

    for (int i = 0; i < p.length - 1; i++) {
      Persion temp = null;
      for (int j = 0; j < p.length - 1 - i; j++) {
        if (p[j].getAge() > p[j + 1].getAge()) {
          temp = p[j];
          p[j] = p[j + 1];
          p[j + 1] = temp;
        }
      }
    }

    for (int i = 0; i < p.length; i++) {
      foo.work(p[i]);
    }

  }

  public void work(Persion p) {
    if (p instanceof Student) {
      ((Student) p).study();
    } else if (p instanceof Teacher) {
      ((Teacher) p).teach();
    } else {
      System.out.println("格式不允许");
    }
  }

}

class Student extends Persion {
  private int stu_id;

  public Student(String name, char sex, int age, int stu_id) {
    super(name, sex, age);
    this.stu_id = stu_id;
  }

  public void study() {
    System.out.println("承诺好好学习");
  }

  public String play() {
    return getName() + super.play() + "足球";
  }

  @Override
  public String toString() {
    return "Student [stu_id=" + stu_id + "]";
  }

  public int getStu_id() {
    return stu_id;
  }

  public void setStu_id(int stu_id) {
    this.stu_id = stu_id;
  }

}

class Teacher extends Persion {
  private int work_age;

  public Teacher(String name, char sex, int age, int work_age) {
    super(name, sex, age);
    this.work_age = work_age;
  }

  public void teach() {
    System.out.println("承诺好好教课");
  }

  public String play() {
    return getName() + super.play() + "象棋";
  }

  public int getWork_age() {
    return work_age;
  }

  public void setWork_age(int work_age) {
    this.work_age = work_age;
  }

}

class Persion {
  private String name;
  private char sex;
  private int age;

  public Persion(String name, char sex, int age) {
    this.name = name;
    this.sex = sex;
    this.age = age;
  }

  public String play() {
    return "爱玩";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public char getSex() {
    return sex;
  }

  public void setSex(char sex) {
    this.sex = sex;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
