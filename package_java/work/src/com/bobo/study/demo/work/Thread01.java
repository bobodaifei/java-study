package com.bobo.study.demo.work;

public class Thread01 {
  public static void main(String[] args) {
    MyThread ticket = new MyThread();
    new Thread(ticket, "张三").start();
    new Thread(ticket, "罗翔").start();
    new Thread(ticket, "黄牛").start();
  }
}

class MyThread extends Thread {
  private int ticketNums = 10;

  @Override
  public void run() {
    while (true) {
      try {
        if (!buy()) {
          break;
        }
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  synchronized public boolean buy() {

    if (ticketNums > 0) {
      ticketNums--;
      System.out.println(Thread.currentThread().getName() + "成功出票，剩余票数为" + ticketNums);
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return true;
    } else {
      System.out.println(Thread.currentThread().getName() + "余票不足，停止售票！");
      return false;
    }
  }

}