package com.bobo.study.chapter15.thread;

public class ExtendThread extends Thread{

  @Override
  public void run() {
    System.out.println("----第一年开始休眠");
    System.out.println(Thread.currentThread().getName());
    System.out.println("----结束休眠");
  }
  
}
