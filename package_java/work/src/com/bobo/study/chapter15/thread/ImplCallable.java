package com.bobo.study.chapter15.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ImplCallable {
  public static void main(String[] args) {
    MyThread1 mt = new MyThread1();

    // 创建一个“未来类”对象
    FutureTask task = new FutureTask(mt);

    // 创建线程对象
    Thread t = new Thread(task);

    t.start();

    try {

      // 通过get()方法获取线程返回值
      System.out.println(task.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    // get()方法会导致当前线程阻塞，所以此方法效率比较低
    // 因为get()方法需要等线程结束后拿到线程返回值
    // 所以main()方法这里的代码需要等get()方法结束才能执行，也就是要等以上线程结束后才执行
    System.out.println("结束了");
  }

  
}

class MyThread1 implements Callable {

  // 这里的call()方法就相当于run()方法
  public String call() throws Exception {
    String str = "hhhh";
    Thread.sleep(5000);
    return str;
  }

}