package com.bobo.study.chapter15.thread;

public class ThreadDaemon01 implements Runnable{

  @Override
  public void run() {
    try {
      while (true) {
        Thread.sleep(400);
        System.out.println("1111");
      }
      
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
  
}
