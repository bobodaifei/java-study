package com.bobo.study.demo.io.print_;

import java.io.FileNotFoundException;
import java.io.PrintStream;

//字节打印流 只有输出
public class PrintStream01 {
  public static void main(String[] args) throws FileNotFoundException {
    // System.outb本身是一个字节流
    PrintStream out = new PrintStream(System.out);

    // public static final PrintStream out = null;
    // 本身就是PrintStream可以直接赋值
    // PrintStream out = System.out;

    out.print("nul111l");
    /*
     * public void print(String s) {
     * //在jdk8中会先判断是否为null，如果为null，则s=null
     * //write(s);
     * write(String.valueOf(s));
     * }
     */

    out.close();
    // 我们可以修改打印流的位置或设备
    System.setOut(new PrintStream("E:/abc.txt"));
    System.out.println("HEOOL FUCK");
  }
}
