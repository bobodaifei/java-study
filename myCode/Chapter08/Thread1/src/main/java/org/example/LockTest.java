package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
  public static void main(String[] args) {
    new LockTest().action();
  }

  private void action() {
    final Outputer outputer = new Outputer();
    new Thread(new Runnable() {
      public void run() {
        for (int i = 0; i < 10; i++) {
          outputer.output("zhangxiaoxiang\n");
        }
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        for (int i = 0; i < 10; i++) {
          outputer.output("lihuoming\n");
        }
      }
    }).start();
  }

  private class Outputer {
    private Lock lock = null;

    public Outputer() {
      lock = new ReentrantLock();
    }

    public void output(String name) {

      lock.lock();
      try {
        for (int i = 0; i < name.length(); i++) {
          System.out.print(name.charAt(i));
        }
        ;

      } finally {
        lock.unlock();
      }
    }

  }
}
