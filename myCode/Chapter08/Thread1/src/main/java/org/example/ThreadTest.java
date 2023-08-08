package org.example;

public class ThreadTest {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    new Thread(new Runnable() {
      public void run() { //1
      }
    }) {
      public void run() { //2
      }
    }.start();





  }


}
