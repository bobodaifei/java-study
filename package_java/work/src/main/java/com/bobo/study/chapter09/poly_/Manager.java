package com.bobo.study.chapter09.poly_;

public class Manager extends Employee {
  private double bonus;
  

  public Manager(String name, double wages, double bonus) {
    super(name, wages);
    this.bonus = bonus;
  }

  public void manage() {
    System.out.println(getName() + "经理管理");
  }

  @Override
  public double getAnnual() {
    return super.getAnnual() + bonus;
  }

  public double getBonus() {
    return bonus;
  }

  public void setBonus(double bonus) {
    this.bonus = bonus;
  }
}
