package org.example;

import java.util.Timer;
import java.util.TimerTask;

public class Timer1 {

  private static int count = 0;
  public static void main(String[] args) {

//    new Timer().schedule(new TimerTask() {
//      @Override
//      public void run() {
//        System.out.println("bombing!");
//      }
//    }, 1000, 2000);
//
//    class MyTimerTask extends TimerTask{
//      @Override
//      public void run() {
//        System.out.println("bombing!");
//        new Timer().schedule(new MyTimerTask() ,1000);
//      }
//    }
//
//    new Timer().schedule(new  MyTimerTask(), 2000);

    class MyTimerTask extends TimerTask {

      @Override
      public void run() {
        count = (count + 1) % 2;
        System.out.println("bombing!");
        new Timer().schedule(new MyTimerTask(), 1000 + count * 1000);
      }
    }

    new Timer().schedule(new MyTimerTask(), 1000);

  }
}

