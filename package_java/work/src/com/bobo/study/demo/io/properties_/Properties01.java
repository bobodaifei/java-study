package com.bobo.study.demo.io.properties_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Properties01 {
  public static void main(String[] args) throws IOException {
    // 旧方式
    BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\Visual Studio Code\\java\\java_study\\package_java\\work\\src/db.properties"));
    String line = "";
    while ((line = bufferedReader.readLine()) != null) {
      String[] split = line.split("=");
      System.out.println(split[1]);
    }
    bufferedReader.close();
  }
}
