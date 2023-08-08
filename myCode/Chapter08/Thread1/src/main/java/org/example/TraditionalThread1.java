package org.example;

public class TraditionalThread1 {

  public static void main(String[] args) {
    new TraditionalThread1().init();
  }

  public void init() {
    Test2 test2 = new Test2();
    new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 1; i <= 50; i++) {
          test2.printSub(i);
        }
      }
    }).start();

    for (int i = 1; i <= 50; i++) {
      test2.printMain(i);
    }
  }

  class Test1 {
    public void printThread() {
      new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 1; i <= 50; i++) {
            synchronized (TraditionalThread1.class) {
              for (int j = 1; j <= 10; j++) {
                System.out.println("sub thread print " + j + "," + "parent thread loop " + i);
              }
            }
          }
        }
      }).start();
      for (int i = 1; i <= 50; i++) {
        synchronized (TraditionalThread1.class) {
          for (int j = 1; j <= 10; j++) {
            System.out.println("main thread print " + j + "," + "parent thread loop " + i);
          }
        }
      }
    }
  }

  public class Test2 {
    private boolean bShouldSub = true;

    public synchronized void printSub(int i) {
      if (!bShouldSub) {
        try {
          this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      for (int j = 1; j <= 10; j++) {
        System.out.println("sub thread print " + j + "," + "parent thread loop " + i);
      }
      bShouldSub = false;
      notify();
    }

    public synchronized void printMain(int i) {
      if (bShouldSub) {
        try {
          this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      for (int j = 1; j <= 100; j++) {
        System.out.println("main thread print " + j + "," + "parent thread loop " + i);
      }
      bShouldSub = true;
      notify();
    }
  }

}
