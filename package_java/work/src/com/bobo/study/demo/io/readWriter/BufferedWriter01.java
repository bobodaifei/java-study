package com.bobo.study.demo.io.readWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * BufferedWriter01
 */
public class BufferedWriter01 {
  public static void main(String[] args) throws IOException {
    //如果要追加，需要写在FileWriter上
    BufferedWriter bWriter = new BufferedWriter(new FileWriter("E:/abc.txt"));
    bWriter.write("这是1");
    bWriter.write("这是1");
    //根据系统插入换行
    bWriter.newLine();
    bWriter.write("这是2");
    bWriter.write("这是3");
    bWriter.write("这是3");
    bWriter.write("这是3");

    bWriter.close();
  }

}