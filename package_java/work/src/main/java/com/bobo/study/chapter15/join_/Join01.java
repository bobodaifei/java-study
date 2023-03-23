package com.bobo.study.chapter15.join_;

public class Join01 {
  public static void main(String[] args) throws InterruptedException {
    T t = new T();
    t.start();

    for (int i = 1; i <= 20; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("主线程线程" + i);
      if (i == 5) {
        // 礼让
        Thread.yield();
        // 插队
        t.join();
      }
    }
  }
}

class T extends Thread {

  @Override
  public void run() {
    for (int i = 1; i <= 20; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("子线程" + i);
    }
  }

  public synchronized static void m1() {
    System.out.println("m1");
  }

  public static void m2() {
    synchronized (T.class){
      System.out.println("m2");
    }
  }
}