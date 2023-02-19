package com.bobo.study.chapter10.interface_;

public class Phone implements UsbInterface{

  @Override
  public void start() {
    // TODO Auto-generated method stub
    System.out.println("phone start");
  }

  @Override
  public void end() {
    // TODO Auto-generated method stub
    System.out.println("phone end");

  }
  
}
