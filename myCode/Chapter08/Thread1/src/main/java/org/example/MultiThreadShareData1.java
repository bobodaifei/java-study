package org.example;

public class MultiThreadShareData1 {

  public static void main(String[] args) {

    MultiThreadShareData1 multiThreadShareData = new MultiThreadShareData1();
    ShareData1 shareData = multiThreadShareData.new ShareData1();
    MyRunnable1 runNable1 = multiThreadShareData.new MyRunnable1(shareData);
    MyRunnable2 runNable2 = multiThreadShareData.new MyRunnable2(shareData);
    new Thread(runNable1).start();
    new Thread(runNable2).start();
  }

  class ShareData1 {
    private int j = 0;

    public ShareData1() {
    }

    public void increment() {
      j++;
    }

    public void decrement() {
      j--;
    }

  }

  class MyRunnable1 implements Runnable {
    private ShareData1 shareData;

    public MyRunnable1(ShareData1 shareData) {
      this.shareData = shareData;
    }

    public void run() {
      this.shareData.increment();
    }

  }

  class MyRunnable2 implements Runnable {

    private ShareData1 shareData;

    public MyRunnable2(ShareData1 shareData) {
      this.shareData = shareData;
    }

    public void run() {
      this.shareData.decrement();
    }

  }

}