package com.bobo.study.demo.io.readWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedCopy01 {
  public static void main(String[] args) throws IOException {
    //不要去操作二进制文件【声音、视频、word、pdf】
    //如果要操作二进制文件则使用BufferedOutputStream和BufferedInputStream
    BufferedWriter bw = new BufferedWriter(new FileWriter("E:/abc11.txt"));
    BufferedReader br = new BufferedReader(new FileReader("E:/abc.txt"));
    //尽量用finally进行关闭
    String str;
    while ((str = br.readLine()) != null) {
      bw.write(str);
      bw.newLine();
    }
    bw.close();
    br.close();
  }
}
