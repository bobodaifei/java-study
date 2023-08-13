package org.example;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

  private static Object object;

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    final Exchanger exchanger = new Exchanger();

    synchronized (object){

    }
    executorService.execute(new Runnable() {



      public void run() {
        try {
          String data1 = "毒品";
          System.out.println("线程" + Thread.currentThread().getName() + "正在把" + data1 + "换出去");
          Thread.sleep((long) (Math.random() * 10000));
          String data2 = (String) exchanger.exchange(data1);
          System.out.println("线程" + Thread.currentThread().getName() + "换回" + data2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    executorService.execute(new Runnable() {
      public void run() {
        try {
          String data1 = "美金";
          System.out.println("线程" + Thread.currentThread().getName() + "正在把" + data1 + "换出去");
          Thread.sleep((long) (Math.random() * 10000));
          String data2 = (String) exchanger.exchange(data1);
          System.out.println("线程" + Thread.currentThread().getName() + "换回" + data2);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

    });

  }

}