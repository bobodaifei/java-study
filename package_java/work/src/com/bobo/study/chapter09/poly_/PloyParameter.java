package com.bobo.study.chapter09.poly_;

public class PloyParameter {
  public static void main(String[] args) {
    Staff staff = new Staff("staff",1000);
    Manager manager = new Manager("manager",1000,2000);
    PloyParameter ployParameter = new PloyParameter();
    
    System.out.println(ployParameter.showEmpAnnual(staff));
    System.out.println(ployParameter.showEmpAnnual(manager));
    ployParameter.testWork(staff);
    ployParameter.testWork(manager);
    
  }

  public double showEmpAnnual(Employee e) {
    return e.getAnnual();
  }

  public void testWork(Employee e) {
    if (e instanceof Staff) {
      ((Staff)e).work();
    }else{
      ((Manager) e).manage();
    }
  }

}



