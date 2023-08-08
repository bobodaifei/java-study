package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServicePool {

  public static void main(String[] args) {
//    ExecutorService threadPool = Executors.newFixedThreadPool(3);
//    ExecutorService threadPool = Executors.newCachedThreadPool();
    ExecutorService threadPool = Executors.newSingleThreadExecutor();
    for (int i = 1; i <= 10; i++) {
      final int taskId = i;
      threadPool.execute(new Runnable() {
        public void run() {
          for (int j = 1; j <= 10; j++) {
            System.out.println(Thread.currentThread().getName() + "----" + j + "次" + "execute task" + taskId);
          }
        }
      });
    }
    //未完成任务就杀死线程
//    threadPool.shutdownNow();
  }
}

class ScheduledExecutorServiceTest{
  public static void main(String[] args) {
//    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//    scheduledExecutorService.schedule(new Runnable() {
//      public void run() {
//        System.out.println("bomb!!!");
//      }
//    },10, TimeUnit.SECONDS);

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
    for (int i = 1; i <= 5; i++) {
      final int taskId = i;
      scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
        public void run() {
          System.out.println("bomb!!!"+taskId);
        }
      }, 2, 2, TimeUnit.SECONDS);
    }
  }
}

