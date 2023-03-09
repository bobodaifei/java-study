package com.bobo.study.demo.work;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Methon01 {
  public static void main(String[] args) throws IOException {
    method01("D:/新建文件夹","D:/Temp");
  }
  public static void method01(String directoryPath1, String directoryPath2) throws IOException {
    File file1 = new File(directoryPath1);
    File file2 = new File(directoryPath2);
    if (!file1.exists()) {
      file1.mkdirs();
    }
    if (!file2.exists()) {
      file2.mkdirs();
    }
    //递归部分
    for (File file : file1.listFiles()) {
      String absolutePath = file.getAbsolutePath();
      if (file.isDirectory()) {
        method01(absolutePath, directoryPath2);
      } else {
        checkFile(file,file2.getAbsolutePath(),".jpg");
      }
    }
  }


  public static void checkFile(File source, String dest, String suffix) throws IOException {
    if (source.getName().endsWith(suffix)) {
      String absolutePath = source.getAbsolutePath();
      String fileName = absolutePath.substring(absolutePath.lastIndexOf(File.separator) + 1);
      copyFile(absolutePath, dest + File.separator + fileName);
    }
  }

  public static void copyFile(String oldPath, String newPath) throws IOException {
    File oldFile = new File(oldPath);
    File file = new File(newPath);
    FileInputStream in = new FileInputStream(oldFile);
    FileOutputStream out = new FileOutputStream(file);

    byte[] buffer = new byte[2097152];
    //将这个流中的字节缓冲到数组buffer中
    while ((in.read(buffer)) != -1) {
      //从指定字节数组写入此文件输出流中
      out.write(buffer);
    }
  }
}
