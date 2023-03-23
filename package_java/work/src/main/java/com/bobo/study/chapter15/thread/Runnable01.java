package com.bobo.study.chapter15.thread;

import com.bobo.study.chapter15.runnable_.ImplRunnable;

public class Runnable01 {
  public static void main(String[] args) throws InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    //cpu内核数
    System.out.println(runtime.availableProcessors());
    
    System.out.println("---start");
    ImplRunnable runnable = new ImplRunnable();
    ImplRunnable runnable1 = new ImplRunnable();
    
    // start只是说明准备就绪 不代表立即工作
    Thread t1 = new Thread(runnable);
    t1.setPriority(5);
    t1.start();
    Thread.sleep(5000);
    System.out.println("---打断t1的休眠");
    t1.interrupt();

    Thread t2 = new Thread(runnable);
    t2.setPriority(5);
    t2.start();


    System.out.println("---吃饭");
    //调度模式
    //分时调度模型 ：平均分配
    //抢占式调度模型 ：看其优先级  java所用
  }
}
