package com.bobo.study.demo.io.inOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStream01 {
  public static void main(String[] args) throws IOException {
    FileInputStream inputStream = new FileInputStream("E:/abc.txt");
    // 如果读到文件末尾，则返回-1
    // int i = inputStream.read();// 从输入流中读取一个数据字节，如果没有输入可用，则此方法将阻塞
    // System.out.println((char)i);//如果是数字或者字母，则可以转char输出（查询ascll码）

    // i = inputStream.read();
    // System.out.println((char)i);
    // i = inputStream.read();
    // System.out.println(i);
    // i = inputStream.read();
    // System.out.println((char)i); //此处已经结束 为-1
    // int i;
    // while((i=inputStream.read())!=-1){//一直读到末尾
    //   System.out.println(i);
    // }

    // 一次性读4个数据字节，返回读取长度
    // byte[] b = new byte[4];
    
    // int count;
    // // inputStream.read(b)返回读取长度
    // while((count=inputStream.read(b))!=-1){
    //   System.out.println(new String(b, 0, count));
    // }

    byte[] b = new byte[4];

    int count;
    //每次读取len个，读取后放到b中下标为1往后的位置，还要防止越界off+len要小于等于4
    while((count=inputStream.read(b,1,3))!=-1){
      System.out.println(new String(b, 1, count));
    }

    inputStream.close();



  }
}
