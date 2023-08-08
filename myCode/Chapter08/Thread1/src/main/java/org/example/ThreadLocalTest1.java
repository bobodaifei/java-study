package org.example;

import java.util.Random;

public class ThreadLocalTest1 {

  public static final ThreadLocal<Person> threadlocal = new ThreadLocal() {
    @Override
    protected Object initialValue() {
      return new Person();
    }
  };

  public static void main(String[] args) {
    new ThreadLocalTest1().init();
  }

  private void init() {
    for (int i = 0; i < 2; i++) {
      new Thread(new Runnable() {
        public void run() {
          int data = new Random().nextInt();
          threadlocal.get().setName(Thread.currentThread().getName());
          threadlocal.get().setAge(data);
          new A().get();
          new B().get();
        }
      }).start();
    }
  }

  //A

  class A {
    Person person = threadlocal.get();

    public void get() {
      System.out.println("A：-" + Thread.currentThread().getName() + ":name:" + person.getName() + ":age:" + person.getAge());
    }
  }

  //B

  class B {
    Person person = threadlocal.get();

    public void get() {
      System.out.println("B：-" + Thread.currentThread().getName() + ":name:" + person.getName() + ":age:" + person.getAge());
    }
  }

  //Person

  static class Person {
    public Person() {
    }

    private String name;
    private int age;

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

  }

}