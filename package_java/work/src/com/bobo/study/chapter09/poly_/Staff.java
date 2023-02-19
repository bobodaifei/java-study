package com.bobo.study.chapter09.poly_;

public class Staff extends Employee{

  public Staff(String name, double wages) {
    super(name, wages);
  }

  public void work() {
    System.out.println(getName() + "员工工作");
  }

  @Override
  public double getAnnual() {
    return super.getAnnual();
  }
}
