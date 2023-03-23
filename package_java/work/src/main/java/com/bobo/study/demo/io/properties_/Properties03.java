package com.bobo.study.demo.io.properties_;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Properties03 {
  public static void main(String[] args) throws IOException {

    //新建properties文件
    Properties properties = new Properties();
    properties.setProperty("charset","utf-8");
    properties.setProperty("user","汤姆");
    properties.setProperty("password","123456");

    properties.store(new FileOutputStream("E:\\Visual Studio Code\\java\\java_study\\package_java\\work\\src/db1.properties"), "这是一个注释");

    System.out.println("success");


  }
}
