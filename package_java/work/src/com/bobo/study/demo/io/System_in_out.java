package com.bobo.study.demo.io;

public class System_in_out {
  public static void main(String[] args) {

    // 标准输入流(字节流),System.in,
    // 编译类型：InputStream
    // 运行类型 BufferedInputStream
    // 标准输入 键盘
    System.out.println(System.in.getClass());
    // 标准输出流,System.out
    // 编译类型 PrintStream
    // 运行类型：PrintStream
    // 标准输出 显示器
    System.out.println(System.out.getClass());
  }
}
