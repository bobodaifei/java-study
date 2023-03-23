package com.bobo.study.demo.io.inOut;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedCopy01 {
  public static void main(String[] args) throws IOException {

    BufferedInputStream in = new BufferedInputStream(new FileInputStream("E:/abc.txt"));
    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("E:/abc112.txt"));
    byte[] b = new byte[1024];

    int count;
    while ((count = in.read(b)) != -1) {
      out.write(b, 0, count);
    }
    
    in.close();
    out.flush();
    out.close();
  }
}
