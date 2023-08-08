package org.example;

import java.util.Random;

public class ThreadLocalTest {

  public static void main(String[] args) {
    new ThreadLocalTest().init();
  }

  //init

  private void init() {
    for (int i = 0; i < 2; i++) {
      new Thread(new Runnable() {
        public void run() {
          int data = new Random().nextInt();
          Person.getThreadInstance().setName(Thread.currentThread().getName());
          Person.getThreadInstance().setAge(data);
          new A().get();
          new B().get();
        }
      }).start();
    }
  }

  //A

  class A {
    Person person = Person.getThreadInstance();

    public void get() {
      System.out.println("A：-" + Thread.currentThread().getName() + ":name:" + person.getName() + ":age:" + person.getAge());
    }
  }

  //B

  class B {
    Person person = Person.getThreadInstance();

    public void get() {
      System.out.println("B：-" + Thread.currentThread().getName() + ":name:" + person.getName() + ":age:" + person.getAge());
    }

  }

  //Person   将跟线程相关的绑定，放在共享的数据类的内部实现

  static class Person {
    private static ThreadLocal<Person> threadLocal = new ThreadLocal<ThreadLocalTest.Person>();

    private Person() {
    }

    public static synchronized Person getThreadInstance() {
      Person person = threadLocal.get();
      if (person == null) {
        person = new Person();
        threadLocal.set(person);
      }
      return person;
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