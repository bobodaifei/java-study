package com.bobo.study.chapter10.abstract_;

abstract public class Emoloyee {
  private String name;
  private int id;
  private double salary;
  public Emoloyee(String name, int id, double salary) {
    this.name = name;
    this.id = id;
    this.salary = salary;
  }

  public abstract void work();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }
  
}
