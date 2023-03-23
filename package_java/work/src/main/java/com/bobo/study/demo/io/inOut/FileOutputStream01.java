package com.bobo.study.demo.io.inOut;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStream01 {
  public static void main(String[] args) throws IOException {
    // append true 追加
    FileOutputStream out = new FileOutputStream("E:/abc.txt", true);
    // 会覆盖之前的内容
    // FileOutputStream out = new FileOutputStream("E:/abc.txt");
    //先写到缓冲区。在输出流关闭之前是可以一直写的的
    // out.write((int)'h');
    // out.write((int)'h');
    // out.write((int)'h');

    //一次写多个内容,可以配合输出流进行拷贝
    out.write("null".getBytes());
    out.write("\n".getBytes());
    out.write("null".getBytes());
    //刷新 立即执行写操作
    out.flush();
    //记得关闭
    out.close();

    


  }



}