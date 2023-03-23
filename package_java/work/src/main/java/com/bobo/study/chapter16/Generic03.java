package com.bobo.study.chapter16;

import java.util.ArrayList;
import java.util.Comparator;

public class Generic03 {
  public static void main(String[] args) {
    ArrayList<Employee> list = new ArrayList();
    Employee emp1 = new Employee("bobo1", 12.5, new Mydate(12, 20, 2000));
    Employee emp2 = new Employee("bobo", 13.5, new Mydate(12, 20, 2010));
    Employee emp3 = new Employee("bobo1", 10.5, new Mydate(12, 10, 2100));
    list.add(emp1);
    list.add(emp2);
    list.add(emp3);
    list.sort(new Comparator<Employee>() {

      @Override
      public int compare(Employee o1, Employee o2) {
        if (o1.getName().compareTo(o2.getName()) != 0) {
          return o1.getName().compareTo(o2.getName());
        }
        return o1.getBirthday().compareTo(o2.getBirthday());
      }
    });

    System.out.println(list);

  }
}

class Employee {
  private String name;
  private double sal;
  private Mydate birthday;

  public Employee(String name, double sal, Mydate birthday) {
    this.name = name;
    this.sal = sal;
    this.birthday = birthday;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSal() {
    return sal;
  }

  public void setSal(double sal) {
    this.sal = sal;
  }

  public Mydate getBirthday() {
    return birthday;
  }

  public void setBirthday(Mydate birthday) {
    this.birthday = birthday;
  }

  @Override
  public String toString() {
    System.out.println("Employee [name=" + name + ", sal=" + sal + ", birthday=" + birthday + "]");
    return "Employee [name=" + name + ", sal=" + sal + ", birthday=" + birthday + "]";
  }

}

class Mydate implements Comparable<Mydate> {
  private int month;
  private int day;
  private int year;

  public Mydate(int month, int day, int year) {
    this.month = month;
    this.day = day;
    this.year = year;
  }

  @Override
  public int compareTo(Mydate mydate) {
    return year - mydate.getYear() == 0
        ? (month - mydate.getMonth() == 0
            ? (day - mydate.getDay() == 0
                ? 0
                : day - mydate.getDay())
            : month - mydate.getMonth())
        : year - mydate.getYear();
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

}

interface InnerGeneric013<T, E> {
  public void name();
}

interface AA<T, E> extends InnerGeneric013<T, E> {

}

class BB implements AA<String, Integer> {

  public static void main(String[] args) {
    BB bb = new BB();
    bb.name("String", 122); //传参时确定泛型的类型
  }

  @Override
  public void name() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'name'");
  }

  public <T, E> void name(T t, E e) {

  }

}