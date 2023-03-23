package com.bobo.study.demo.io.readWriter;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class FileReader_ {
  public static void main(String[] args) throws IOException {
    String filePath = "E:/abc.txt";
    FileReader fileReader = new FileReader(filePath);

    // 单个字符读取
    // int count;
    // while ((count = fileReader.read())!=-1) {
    // System.out.println((char)count);
    // }

    char[] buffer = new char[4];
    int count;
    // 返回读取到的个数
    while ((count = fileReader.read(buffer)) != -1) {
      System.out.println(new String(buffer,0,count));
    }

    fileReader.close();


  }

}

//节点流
//节点流可以从一个特定的数据源读写数据，如FileReader、FileWriter
//分为字节和字符流 视情况使用

//处理流（包装流）
// 处理流(也叫包装流)是“连接”在已存在的流（节点流或处理流)之上，为程序提供更为强大的读写功能,如BufferedReader、BufferedWriter
//可以封装节点流（只要是reader或writer的子类）
//private Writer out;
//private char cb[];

//节点和处理区别
//节点流是底层流/低级流,直接跟数据源相接。
//处理流(包装流)包装节点流，既可以消除不同节点流的实现差异，也可以提供更方便的方法来完成输入输出 。
//处理流(也叫包装流)对节点流进行包装，使用了修饰器设计模式，不会直接与数据源相连[模拟修饰器设计模式]

//处理流的功能主要体现在以下两个方面:
//性能的提高:主要以增加缓冲的方式来提高输入输出的效率。
//操作的便捷:处理流可能提供了一系列便捷的方法来一次输入输出大批量的数据,使用更加灵活方便

