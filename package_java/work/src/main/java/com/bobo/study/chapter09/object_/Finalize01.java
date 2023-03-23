package com.bobo.study.chapter09.object_;

public class Finalize01 {
  public static void main(String[] args) {
    Car bmw = new Car("宝马");
    //销毁前会调用对象的finalize方法
    //不会实时回收
    bmw = null;//
    System.gc();

    System.out.println("程序退出了");
  }
}
class Car{
  private String name;

  public Car(String name) {
    this.name = name;
  }

  @Override
  protected void finalize() throws Throwable {
    System.out.println("释放了car的前置资源");
    super.finalize();
  }



  
}
