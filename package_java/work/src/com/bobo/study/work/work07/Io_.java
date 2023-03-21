package com.bobo.study.work.work07;

import java.io.File;

public class Io_ {
  public static void main(String[] args) {
    createFile("E:\\新建文件夹 (2)\\asdasd\\asdasd", null);
  }

  /*
   * 1、封装一个方法，参数为String filePath，String
   * fileName,要求根据filePath创建文件夹并且能够根据fileName创建出对应的文件。
   * 2、封装一个方法，参数为File directory,int count ，要求能够遍历出该文件夹下所有文件名称，并控制台输出。
   * 3、封装一个方法，参数为File
   * directory，要求找到该文件夹下面的aa.txt，如果没有就创建aa.txt，并且在a.txt文件中输入“china number one”
   * 4、封装一个方法、参数为File directory,要求将该目录下所有类型是文件的文件的路径在控制台展示出来
   * 5、封装一个方法、参数为String directoryPatch,int count,要求递归调用，遍历出该文件夹下面所以所以文件，并控制台输出
   * 6、封装一个方法、参数为String directoryPath,要求删除该文件夹下面所有“.jpg”的文件
   * 7、封装一个方法、参数为String directoryPath，要求获取该文件加下面所有txt文件的路径，并输出。
   * 
   */

  // 1、封装一个方法，参数为String filePath，String
  // fileName,要求根据filePath创建文件夹并且能够根据fileName创建出对应的文件。
  public static void createFile(String filePath, String fileName) {
    if (new File(filePath).exists()) {
      
    }
  }
}
