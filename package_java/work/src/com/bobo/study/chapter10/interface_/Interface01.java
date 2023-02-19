package com.bobo.study.chapter10.interface_;

public class Interface01 {
  public static void main(String[] args) {
    Phone phone = new Phone();

    Camera camera = new Camera();
    
    
    Computer computer = new Computer();
    computer.work(camera);
    computer.work(phone);
    
  }
}
