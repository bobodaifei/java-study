package com.bobo.study.demo.work;

import java.io.File;

public class File01 {
  public static void main(String[] args) {
    file_("D:/circle.png");
    file_("D:/");
  }
  public static void file_(String filePath) {
    File file = new File(filePath);
    if (file.isFile()) {
      System.out.println(file.getName());
    } 
    if (file.isDirectory()) {
      for (String str : file.list()) {
        System.out.println(str);
      }
    }
  }
}
