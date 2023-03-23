package com.bobo.study.chapter09.polyarr_;


public class PloyArray {
  public static void main(String[] args) {
    Persion[] persions = new Persion[5];
    Persion p1 = new Persion("p1",12);
    Persion s1 = new Student("s1", 12,56);
    Persion s2 = new Student("s2", 122, 5456);
    Persion t1 = new Teacher("t1", 152, 546);
    Persion t2 = new Teacher("t2", 182, 3456);
    persions[0]=p1;
    persions[1]=s1;
    persions[2]=s2;
    persions[3]=t1;
    persions[4]=t2;
    for (int i = 0; i < persions.length; i++) {
      if (persions[i] instanceof Student) {
        ((Student) persions[i]).study();
      }
      if (persions[i] instanceof Teacher) {
        ((Teacher) persions[i]).teach();
      }
      // System.out.println(persions[i].say());
    }

    // Student s11 = (Student)s1;
    // s11.study();
  }
}

class Persion {
  String name;
  int age;

  public Persion(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Persion() {
  }

  public String say() {
    return "name=" + name + ", age=" + age;
  }

}

class Student extends Persion {
  int score;

  public Student() {
  }

  public Student(String name, int age, int score) {
    super(name, age);
    this.score = score;
  }

  public void study() {
    System.out.println(name+"正在学习");
  }

  @Override
  public String say() {
    return super.say()+"score=" + score + "]";
  }
  
}

class Teacher extends Persion {
  int salary;

  public Teacher() {
  }

  public Teacher(String name, int age, int salary) {
    super(name, age);
    this.salary = salary;
  }

  public void teach() {
    System.out.println(name + "正在教课");
  }

  @Override
  public String say() {
    return super.say() + "salary=" + salary + "]";
  }

}