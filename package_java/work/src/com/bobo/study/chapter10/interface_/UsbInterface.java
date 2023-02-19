package com.bobo.study.chapter10.interface_;

public interface UsbInterface {
  public final String name = "usb";

  public void start();
  
  public void end();
  public static void end1(){
    
  };

  default public void name(){
    System.out.println("本质是usb的使用");
  }
}
