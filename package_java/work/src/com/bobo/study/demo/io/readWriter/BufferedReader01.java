package com.bobo.study.demo.io.readWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReader01 {
  public static void main(String[] args) throws IOException {

    
    // BufferedReader和 BufferedWriter属于字符流，是按照字符来读取数据的
    // 关闭时，只需要关闭外层流即可,会调用内层的close



    BufferedReader bReader = new BufferedReader(new FileReader("E:/abc.txt"));

    //按行读取
    String line;
    // 当返回一个null时则读取完毕
    

    while ((line = bReader.readLine())!=null) {
      System.out.println(line);
    }
    bReader.close();
    // public void close() throws IOException {
    //     synchronized (lock) {
    //         if (in == null)
    //             return;
    //         try {
      //此处关闭了内层流
    //             in.close();
    //         } finally {
    //             in = null;
    //             cb = null;
    //         }
    //     }
    // }


  }
}
