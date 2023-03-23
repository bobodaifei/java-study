package com.bobo.study.chapter12.exception_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Throws01 {
  public static void main(String[] args) {
    try {
      f1();
    } catch (NullPointerException e) {
      System.out.println(e);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void f1() throws FileNotFoundException  {
    int i = 10/0;
    FileInputStream foo = new FileInputStream("null");
    
  }
}
