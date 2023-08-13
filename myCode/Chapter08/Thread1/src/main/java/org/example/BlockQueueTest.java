package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest {

  public static void main(String[] args) {
    BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
    for (int i = 1; i <= 2; i++) {
      new Thread(new Runnable() {
        public void run() {
          while (true) {
            try {
              Thread.sleep((long) (Math.random() * 10000));
              System.out.println(Thread.currentThread().getName() + "准备放数据");
              blockingQueue.put(1);
              System.out.println(Thread.currentThread().getName() + "放数据成功" + "当前队列有" + blockingQueue.size() + "个数据");
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      }).start();
    }

    new Thread(new Runnable() {
      public void run() {
        while (true) {
          try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println(Thread.currentThread().getName() + "准备取数据!");
            blockingQueue.take();
            System.out.println(Thread.currentThread().getName() + "取数据成功" + "当前队列有" + blockingQueue.size() + "个数据");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }
}


class BlockingQueueCommunicationTest {

  public static void main(String[] args) {
    new BlockingQueueCommunicationTest().execute();
  }

  private void execute() {

    final Business business = new Business();
    new Thread(new Runnable() {
      public void run() {
        for (int j = 1; j <= 100; j++) {
          business.sub(j);
        }
      }
    }).start();
    for (int j = 1; j <= 100; j++) {
      business.main(j);
    }
  }

  private class Business {
    BlockingQueue blockingQueue1 = new ArrayBlockingQueue(1);
    BlockingQueue blockingQueue2 = new ArrayBlockingQueue(1);
    //匿名构造方法，先于非匿名构造方法执行

    {

      try {
        blockingQueue2.put(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    public void sub(int j) {

      try {
        blockingQueue1.put(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      for (int i = 1; i <= 10; i++) {
        System.out.println("sub thread sequece of " + i + ",loop of " + j);
      }

      try {
        blockingQueue2.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    public void main(int j) {
      try {
        blockingQueue2.put(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      for (int i = 1; i <= 10; i++) {
        System.out.println("main thread sequece of " + i + ",loop of " + j);
      }
      try {
        blockingQueue1.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
