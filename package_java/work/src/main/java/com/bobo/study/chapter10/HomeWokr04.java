package com.bobo.study.chapter10;

public class HomeWokr04 {
  public static void main(String[] args) {
    CellPhone cellPhone = new CellPhone();
    cellPhone.testWork(new Calculation() {

      @Override
      public double work(double d1, double d2) {
        // TODO Auto-generated method stub
        System.out.println("working ");
        return d1 + d2;
      }

    }, 3.6, 56.5);
  }
}

interface Calculation {
  public double work(double d1, double d2);
}

class CellPhone {

  public void testWork(Calculation calculation, double d1, double d2) {
    System.out.println(calculation.work(d1, d2));
  }

}