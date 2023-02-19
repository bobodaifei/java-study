package com.bobo.study.chapter09.poly_;

public class Employee {
  private String name;
  private double wages;

  

  public Employee(String name, double wages) {
    this.name = name;
    this.wages = wages;
  }

  public double getAnnual() {
    return wages * 12;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getWages() {
    return wages;
  }

  public void setWages(double wages) {
    this.wages = wages;
  }
}
