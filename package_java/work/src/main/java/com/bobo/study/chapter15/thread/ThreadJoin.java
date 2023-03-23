package com.bobo.study.chapter15.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.bobo.study.chapter15.runnable_.ImplRunnable;

public class ThreadJoin {
  public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
    System.out.println("---做好饭了");
    ImplRunnable runnable = new ImplRunnable();
    CyclicBarrier cb = new CyclicBarrier(2 + 1);// 注意：10个子线程 + 1个主线程

    // start只是说明准备就绪 不代表立即工作
    Thread t1 = new Thread(runnable);
    t1.setPriority(5);
    t1.start();

    t1.join();

    Thread t2 = new Thread(runnable);
    t2.setPriority(5);
    t2.start();
    Thread t3 = new Thread(runnable);
    t3.setPriority(5);
    t3.start();
    cb.await();

    System.out.println("---吃饭");
  }
}
