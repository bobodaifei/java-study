package com.bobo.study.chapter10.Inner_;

public class InnerExercise01 {
  public static void main(String[] args) {
    CellPhone cPhone = new CellPhone();
    
    cPhone.alarmClock(new Bell() {

      @Override
      public void ring() {
        // TODO Auto-generated method stub
        System.out.println("fuck");
      }

    });
  }
}

interface Bell {
  public void ring();
}



class CellPhone {

  public void alarmClock(Bell bell) {
    bell.ring();
  }

}
