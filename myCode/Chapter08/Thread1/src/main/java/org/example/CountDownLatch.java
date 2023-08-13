package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatch {
  public static void main(String[] args) {

    ExecutorService executorService = Executors.newCachedThreadPool();
    final java.util.concurrent.CountDownLatch orderCount = new java.util.concurrent.CountDownLatch(1);
    final java.util.concurrent.CountDownLatch ansCount = new java.util.concurrent.CountDownLatch(3);
    for (int i = 1; i <= 3; i++) {
      Runnable runnable = new Runnable() {
        public void run() {
          System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");
          try {
            orderCount.await();
            System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");
            ansCount.countDown();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      executorService.execute(runnable);
    }

    try {
      Thread.sleep((long) (Math.random() * 10000));
      System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");
      orderCount.countDown();
      System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果");
      ansCount.await();
      System.out.println("线程" + Thread.currentThread().getName() + "已收到所有响应结果");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    executorService.shutdown();
  }

}
