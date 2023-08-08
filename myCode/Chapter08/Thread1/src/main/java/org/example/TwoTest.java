package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TwoTest {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    final Semaphore semaphore = new Semaphore(3);
    for (int i = 0; i < 10; i++) {
      Runnable runnable = new Runnable() {
        public void run() {
          try {
            semaphore.acquire();
            System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
            semaphore.release();
            //下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
            System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      executorService.execute(runnable);
    }
  }
}