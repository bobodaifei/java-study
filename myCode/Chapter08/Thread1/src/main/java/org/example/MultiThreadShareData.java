package org.example;

public class MultiThreadShareData {
  private static ShareData shareData = new ShareData();

  public static void main(String[] args) {
    MyRunnable1 runNable1 = new MyRunnable1(shareData);
    MyRunnable2 runNable2 = new MyRunnable2(shareData);
    new Thread(runNable1).start();
    new Thread(runNable2).start();
  }
}

class ShareData {
  private int j = 0;

  public ShareData() {
  }

  public void increment() {
    j++;
  }

  public void decrement() {
    j--;
  }

}

class MyRunnable1 implements Runnable {
  private ShareData shareData;

  public MyRunnable1(ShareData shareData) {
    this.shareData = shareData;
  }

  public void run() {
    this.shareData.increment();
  }
}

class MyRunnable2 implements Runnable {
  private ShareData shareData;

  public MyRunnable2(ShareData shareData) {
    this.shareData = shareData;
  }

  public void run() {
    this.shareData.decrement();
  }

}