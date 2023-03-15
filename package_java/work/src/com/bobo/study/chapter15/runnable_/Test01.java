package com.bobo.study.chapter15.runnable_;

/**
 * Test01
 */
public class Test01 {
  public static void main(String[] args) {
    Thread thread = new Thread(new ImplRunnable());
    thread.start();

  }

}

class ThreadProxy implements Runnable {
  private Runnable target = null;

  public ThreadProxy() {
  }

  public ThreadProxy(Runnable target) {
    this.target = target;
  }

  @Override
  public void run() {
    if (target != null) {
      target.run();
    }
  }

  public void start() {
    start0();
  }

  private void start0() {
    run();
  }

}