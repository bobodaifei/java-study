package com.bobo.study.chapter15.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadDaemon {
  public static void main(String[] args) throws InterruptedException {
    ThreadDaemon01 runnable = new ThreadDaemon01();
    Thread t1 = new Thread(runnable);
    //守护线程
    t1.setDaemon(true);
    t1.start();
    Thread.sleep(10000);
  }
}

class MyThread extends TimerTask {
  // 指定定时任务run()代码块
  public void run() {
    System.out.println("hhhh");
  }
  
  public static void main(String[] args) {
    // 创建定时器对象
    Timer ti = new Timer();

    // 创建定时任务对象
    TimerTask task = new MyThread();

    // 任务第一次执行的时间
    Date firstTime = new Date();

    // 任务task从时间date开始执行，每隔2000ms执行一次
    ti.schedule(task, firstTime, 2000);

  }
}