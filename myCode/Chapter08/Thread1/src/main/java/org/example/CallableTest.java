package org.example;

import java.util.Random;
import java.util.concurrent.*;

public class CallableTest {
  public static void main(String[] args) {
    System.out.println("主线程：：：：" + Thread.currentThread().getName());
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future future = executorService.submit(new Callable() {
      public Object call() throws Exception {
        Thread.sleep(2000);
        return Thread.currentThread().getName();
      }
    });
    String string = null;
    try {
      System.out.println("等待开始");
      string = (String) future.get();//没有结果会一直等待，知道有结果为止
      //string = (String) future.get(10, TimeUnit.SECONDS);**//等待10s，没有有结果报异常**
      System.out.println("等待结束");
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Callable线程：：：：" + string);


  }
}

class CallableTest1 {
  public static void main(String[] args) {
    ExecutorService executorService1 = Executors.newFixedThreadPool(10);
    CompletionService completionService = new ExecutorCompletionService(executorService1);
    for (int i = 1; i <= 10; i++) {
      final int taskId = i;
      completionService.submit(new Callable() {
        public Object call() throws Exception {
          Thread.sleep(new Random().nextInt(5000));
          return "执行完的任务的ID::::" + taskId;
        }
      });
    }

    for (int i = 1; i <= 10; i++) {
      try {
        String string1 = (String) completionService.take().get();
        System.out.println(string1);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
