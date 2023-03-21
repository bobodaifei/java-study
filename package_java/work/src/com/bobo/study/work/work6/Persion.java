package com.bobo.study.work.work6;

public class Persion {

  public static void main(String[] args) {
    Child child = new Child("null", 100, "null");
    System.out.println(new Persion("null", 0, "null").getSleepTime());
    System.out.println(new MyTest().getMyPerson("null", 0, "null"));
    System.out.println(child.getSleepTime());
    System.out.println(new MyTest01().getMyPerson("qqq", 0, "nan", "Child").getSleepTime());
    System.out.println(new MyTest().toSleep(child));
  }

  private String name;
  private int age;
  private String gender;

  public Persion(String name, int age, String gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public int getSleepTime() {

    return 8;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "Persion [name=" + name + ", age=" + age + ", gender=" + gender + "]";
  }
  
}

class MyTest {
  public Persion getMyPerson(String name, int age, String gender) {
    return new Persion(name, age, gender);
  }

  public int toSleep(Persion persion) {
    return persion.getSleepTime();
  }
}

class Child extends Persion {

  public Child(String name, int age, String gender) {
    super(name, age, gender);
  }

  @Override
  public int getSleepTime() {
    return 10;
  }
}

class MyTest01 extends MyTest {

  public Persion getMyPerson(String name, int age, String gender, String personType) {

    switch (personType) {
      case "Child":
        return new Child(name, age, gender);
      default:
        return super.getMyPerson(name, age, gender);
    }

  }
}