package com.bobo.study.demo.io.print_;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//字符打印流 只有输出
public class PrintWriter01 {
  public static void main(String[] args) throws IOException {
    
    //字节流转字符流
    /*
     * public PrintWriter(OutputStream out) {
     *  this(out, false);
     * }
     */
    // 传参只要是 OutputStream的子类就可以 这里System.out是当前设备输出
    // PrintWriter printWriter = new PrintWriter(System.out);
    PrintWriter printWriter = new PrintWriter(new FileWriter("E:/abc.txt"));
    printWriter.print("HI FUCCO111");

    printWriter.close();
    
  }
}
