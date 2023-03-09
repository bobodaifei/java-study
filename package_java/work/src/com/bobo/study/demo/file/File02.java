package com.bobo.study.demo.file;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class File02 {
  public static void main(String[] args) {
    // 目录操作
    File file = new File("D:/gcc");
    file.mkdir();// 创建一个目录 返回Boolean 只能创建一级目录
    File file2 = new File("D:/gcc/a/v");
    file.mkdirs();// 创建多级目录
    file.delete();// 删除目录 如果内有文件/目录 则删除失败（需要递归删除）
    file.renameTo(file2);
    file.list();
    for (String string : file.list()) { // String[]
      // 调用get方法
      System.out.println(string);
    }
    for (File f : file.listFiles()) { // File[]
      // 调用get方法
      System.out.println(f);
    }
    File[] f1 = file.listFiles(new FileFilter() {

      @Override
      public boolean accept(File pathname) {
        if (pathname.getName().contains("a")) {
          return true;
        }
        return false;
      }

    });
  }
}
