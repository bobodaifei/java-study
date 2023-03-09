package com.bobo.study.chapter15.thread;

public class ImplRunnable implements Runnable{

  @Override
  public void run() {
    System.out.println("----第一年开始休眠");
    for (int i = 0; i < 10; i++) {
      System.out.println(i);
    }
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("----结束休眠");
  }
  
}
