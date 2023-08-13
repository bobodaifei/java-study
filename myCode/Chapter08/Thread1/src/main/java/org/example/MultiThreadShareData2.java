package org.example;

public class MultiThreadShareData2 {

//  static ShareData2 shareData = new ShareData2();

  public static void main(String[] args) {
    final ShareData2 shareData = new ShareData2();
    new Thread(new Runnable() {
      public void run() {
        shareData.increment();
      }

    }).start();

    new Thread(new Runnable() {
      public void run() {
        shareData.decrement();
      }
    }).start();

  }

}

class ShareData2 {

  private int j = 0;

  public ShareData2() {
  }

  public void increment() {
    j++;
  }

  public void decrement() {
    j--;
  }

}
