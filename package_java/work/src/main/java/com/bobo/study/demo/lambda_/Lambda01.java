package com.bobo.study.demo.lambda_;

public class Lambda01 {
  /**
   * @param args
   */
  public static void main(String[] args) {
    
    //例一
    new Thread(new Runnable() {

      @Override
      public void run() {
        System.out.println("奥里给");
      }
      
    }).start();

    new Thread(()-> System.out.println("aoligei ")).start();

    

  }


}
