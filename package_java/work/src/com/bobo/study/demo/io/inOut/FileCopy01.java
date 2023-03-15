package com.bobo.study.demo.io.inOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy01 {
  public static void main(String[] args) throws IOException {
    FileInputStream in = new FileInputStream("E:/abc.txt");
    FileOutputStream out = new FileOutputStream("E:/abc11.txt");
    byte[] b1 = new byte[3];
    
    int count;
    while ((count = in.read(b1))!=-1) {
      out.write(b1,0,count);
    }
    out.flush();
    in.close();
    out.close();


  }
}
