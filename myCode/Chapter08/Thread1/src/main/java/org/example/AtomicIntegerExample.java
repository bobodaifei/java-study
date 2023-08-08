package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
  private static AtomicInteger counter = new AtomicInteger(0);

  public static void main(String[] args) {
    Thread thread1 = new Thread(new IncrementTask());
    Thread thread2 = new Thread(new IncrementTask());

    thread1.start();
    thread2.start();

    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Counter: " + counter.get());
  }

  static class IncrementTask implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        counter.incrementAndGet();
      }
    }
  }
}
