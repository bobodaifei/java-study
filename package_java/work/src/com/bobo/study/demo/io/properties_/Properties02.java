package com.bobo.study.demo.io.properties_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Properties02 {
  public static void main(String[] args) throws FileNotFoundException, IOException {
    // load: 加载配置文件的键值对到Properties对象
    // list:将数据显示到指定设备
    // getProperty(key):根据键获取值
    // setProperty(key,value):设置键值对到Properties对象
    // store:将Properties中的键值对存储到配置文件,在idea中，保存信息到配置文件,如果含有中文，会存储为unicode码

    // 实现的是Hashtable
    Properties properties = new Properties();
    properties.load(new FileInputStream("E:\\Visual Studio Code\\java\\java_study\\package_java\\work\\src/db1.properties"));

    properties.list(System.out);


    String property = properties.getProperty("password");
    System.out.println(property);



  }
}
