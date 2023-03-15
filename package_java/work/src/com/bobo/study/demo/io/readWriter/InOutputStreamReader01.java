package com.bobo.study.demo.io.readWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class InOutputStreamReader01 {
  public static void main(String[] args) throws IOException {
    // lnputStreamReader:Reader的子类，可以将InputStream(字节流)包装成(转换)Reader(字符流)
    // OutputStreamWriter:Writer的子类，实现将OutputStream(字节流)包装成Writer(字符流)
    // 当处理纯文本数据时，如果使用字符流效率更高，并且可以有效解决中文问题,所以建议将字节流转换成字符流
    // 可以在使用时指定编码格式(比如utf-8, gbk , gb2312, ISO8859-1等)

    // InputStreamReader重要构造器
    // public InputStreamReader(InputStream in, Charset cs)
    // 可以传入InputStream对象(字节输入流的顶级对象)和其指定处理编码
    String filePath = "E:/abc.txt";
    // FileInputStream转成 InputStream
    // 同时指定了编码
    //希望使用bufferedReader进行操作（强大）
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "gbk"));
    String readLine = br.readLine();
    System.out.println(readLine);
    br.close();

    // OutputStreamWriter重要构造器
    // public OutputStreamWriter(OutputStream out, Charset cs)
    // 可以传入OutputStream对象(字节输出流的顶级对象)和其指定处理编码
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, false),"gbk"));
    
    bw.write("狗贼");
    bw.newLine();
    bw.write("狗贼");
    bw.close();
  }
}
