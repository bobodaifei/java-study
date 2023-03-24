package com.bobo.study.work.work;

public class Thread01 {
  public static void main(String[] args) {
    // MyThread ticket = new MyThread();
    // new Thread(ticket, "张三").start();
    // new Thread(ticket, "罗翔").start();
    // new Thread(ticket, "黄牛").start();
    // SellTick01 sellTick01 = new SellTick01();
    // SellTick01 sellTick02 = new SellTick01();
    // SellTick01 sellTick03 = new SellTick01();
    // sellTick01.start();
    // sellTick02.start();
    // sellTick03.start();
    Thread thread1 = new Thread(new SellTick02(), "张三");
    Thread thread2 = new Thread(new SellTick02(), "里斯");
    Thread thread3 = new Thread(new SellTick02(), "王五");
    thread1.start();
    thread2.start();
    thread3.start();

    // SellTick01 ticket = new SellTick01();
    // new Thread(ticket, "张三").start();
    // new Thread(ticket, "里斯").start();
    // new Thread(ticket, "王五").start();

  }
}

// class MyThread extends Thread {
// private int ticketNums = 10;

// @Override
// public void run() {
// while (true) {
// try {
// if (!buy()) {
// break;
// }
// Thread.sleep(1000);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// }

// synchronized public boolean buy() {

// if (ticketNums > 0) {
// ticketNums--;
// System.out.println(Thread.currentThread().getName() + "成功出票，剩余票数为" +
// ticketNums);
// try {
// Thread.sleep(200);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// return true;
// } else {
// System.out.println(Thread.currentThread().getName() + "余票不足，停止售票！");
// return false;
// }
// }

// }

class SellTick01 extends Thread {
  private int num = 100;

  @Override
  public void run() {
    while (true) {
      synchronized (this) {
        if (num <= 0) {
          System.out.println("售票结束");
          break;
        }
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "剩余票数=" + (--num));
      }
    }
  }
}

class SellTick02 implements Runnable {
  private static int num = 10;

  @Override
  public void run() {
    while (true) {
      synchronized (SellTick02.class) {
        if (num <= 0) {
          System.out.println("售票结束");
          break;
        }
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "剩余票数=" + (--num));
      }
    }
  }
}