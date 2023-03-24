package com.bobo.study.chapter15.thread;

public class Thread01 {
  public static void main(String[] args) {
    ExtendThread thread = new ExtendThread();
    thread.start();
    //start只是说明准备就绪 不代表立即工作
    // thread.setPriority(5);
    // thread.start();

    // Thread foo = new Thread(thread);
    // Thread foo2 = new Thread(thread);
    // Thread foo1 = new Thread(thread,"线程1");
    // foo.start();
    // foo1.start();
    // foo2.start();
    // System.out.println("---吃饭");
    
  }
}
