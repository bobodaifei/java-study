package com.bobo.study.chapter10.abstract_;

public class Manager extends Emoloyee{
  private double bonus;

  

  

  public Manager(String name, int id, double salary, double bonus) {
    super(name, id, salary);
    this.bonus = bonus;
  }



  @Override
  public void work() {
    // TODO Auto-generated method stub
    System.out.println("经理 "+this.getName()+"工作中");
  }



  public double getBonus() {
    return bonus;
  }



  public void setBonus(double bonus) {
    this.bonus = bonus;
  }

}

class text{
  public static void main(String[] args) {
    Manager foo = new Manager("tom",888,20000,300);
    foo.work();
  }
}
