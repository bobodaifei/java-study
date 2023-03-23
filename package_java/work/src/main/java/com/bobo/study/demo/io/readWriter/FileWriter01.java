package com.bobo.study.demo.io.readWriter;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriter01 {
  public static void main(String[] args) throws IOException {
    String filePath = "E:/abc.txt";
    FileWriter fileWriter = new FileWriter(filePath);

    // 写入单个字符
    fileWriter.write('H');
    // 写入单个字符串
    fileWriter.write("xin");
    fileWriter.write("xin",1,2);
    // 写入指定char数组
    char[] chars = { 'a', 'b' };
    fileWriter.write(chars);
    fileWriter.write(chars, 0, 2);

    //这两个的底层都是FileOutput
    fileWriter.flush();
    fileWriter.close();

  }
}
