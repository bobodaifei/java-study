package com.bobo.study.demo.file;

import java.io.File;
import java.io.IOException;

public class File01 {
  public static void main(String[] args) throws IOException {
    File file = new File("E:/abc.txt");//文件路径
    file = new File("E:/", "abc.txt");//与上面一样
    file = new File("E:/abc11.txt");
    file.createNewFile();//如果存在就不会再创建了 返回Boolean
    file.delete();//同上 删除文件
    file.exists();//判断文件或文件夹是否存在
    File file1 = file.getAbsoluteFile();// 该函数返回表示与抽象路径名相同的文件或目录的绝对文件对象。
    String str = file1.getPath();//文件路径
    File file2 = file.getParentFile();//上层文件目录
    file2.isDirectory();//返回Boolean 判断是否是目录
    file2.isFile();//返回Boolean 判断是否是标准文件
    file2.length();//返回文件字节
    file2.getName();//返回文件名
    
  }
}
