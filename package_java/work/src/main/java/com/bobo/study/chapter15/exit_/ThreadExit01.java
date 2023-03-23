package com.bobo.study.chapter15.exit_;

public class ThreadExit01 {
  public static void main(String[] args) {
    T t1 = new T();
    t1.start();
    
  }
}

class T extends Thread{

  private boolean loop = true;
  @Override
  public void run() {
    while (loop) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("run....");
    }
  }
  public void setLoop(boolean loop) {
    this.loop = loop;
  }
  
}
