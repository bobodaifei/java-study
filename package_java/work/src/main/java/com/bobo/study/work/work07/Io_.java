package com.bobo.study.work.work07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class Io_ {
  public static void main(String[] args) throws IOException {
    // createFile("E:\\新建文件夹 (2)\\asdasd\\asdasd", "aaa.txt");
    // getFileList(new File("E:\\新建文件夹 (2)"), 0);
    findFile(new File("E:\\新建文件夹 (2)"));
    // showFileDirectory(new File("E:\\新建文件夹 (2)"));
    // getFileList("E:\\新建文件夹 (2)", 0);
    // deleteJpg("E:\\新建文件夹 (2)");
    // getAllTxtFile("E:\\新建文件夹 (2)");

  }

  // 1、封装一个方法，参数为String filePath，String
  // fileName,要求根据filePath创建文件夹并且能够根据fileName创建出对应的文件。
  public static void createFile(String filePath, String fileName) throws IOException {
    File file = new File(filePath);
    if (!file.exists()) {
      file.mkdirs();
    }
    File file2 = new File(filePath, fileName);
    if (!file2.exists()) {
      file2.createNewFile();
    }
  }

  // 2、封装一个方法，参数为File directory,int count，要求能够遍历出该文件夹下所有文件名称，并控制台输出。
  public static void getFileList(File directory, int count) {
    for (int i = 0; i < count; i++) {
      System.out.print(" ");
    }
    System.out.println(directory.getName());

    for (File file : directory.listFiles()) {
      if (file.isFile()) {
        for (int i = 0; i < count + 1; i++) {
          System.out.print(" ");
        }
        System.out.println(file.getName());
      } else {
        getFileList(file, ++count);
      }
    }
  }

  // 3、封装一个方法，参数为File
  // directory，要求找到该文件夹下面的aa.txt，如果没有就创建aa.txt，并且在a.txt文件中输入“china number one”
  public static void findFile(File directory) throws IOException {
    if (findFile01(directory)) {
      System.out.println("找到了");
    }else{
      File file = new File(directory, "aa.txt");
      file.createNewFile();
      System.out.println("创建了");
    }
  }

  public static boolean findFile01(File directory) {
    for (File file : directory.listFiles()) {
      if ("aa.txt".equals(file.getName()) && file.isFile()) {
        FileOutputStream out = null;
        try {
          out = new FileOutputStream(file);
          out.write("china number one".getBytes());
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try {
            out.close();
            return true;
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      } else if (file.isDirectory()) {
        if (findFile01(file)) {
          return true;
        }
      }
    }
    return false;
  }

  // 4、封装一个方法、参数为File directory,要求将该目录下所有类型是文件的文件的路径在控制台展示出来
  public static void showFileDirectory(File directory) {
    for (File file : directory.listFiles()) {
      if (file.isFile()) {
        System.out.println(file.getPath());
      } else {
        showFileDirectory(file);
      }
    }
  }

  // 5、封装一个方法、参数为String directoryPatch,int count,要求递归调用，遍历出该文件夹下面所以所以文件，并控制台输出
  public static void getFileList(String directoryPatch, int count) {
    for (int i = 0; i < count; i++) {
      System.out.print(" ");
    }
    System.out.println(directoryPatch);

    for (File file : new File(directoryPatch).listFiles()) {
      if (file.isFile()) {
        for (int i = 0; i < count + 1; i++) {
          System.out.print(" ");
        }
        System.out.println(file.getName());
      } else {
        getFileList(file, ++count);
      }
    }
  }

  // 6、封装一个方法、参数为String directoryPath,要求删除该文件夹下面所有“.jpg”的文件
  public static void deleteJpg(String directoryPath) throws IOException {
    for (File file : new File(directoryPath).listFiles()) {
      if (file.getName().endsWith(".jpg") && file.isFile()) {
        file.delete();
      } else if (file.isDirectory()) {
        deleteJpg(file.getCanonicalPath());
      }
    }
  }

  // 封装一个方法、参数为String directoryPath，要求获取该文件加下面所有txt文件的路径，并输出。
  public static void getAllTxtFile(String directoryPath) throws IOException {
    for (File file : new File(directoryPath).listFiles()) {
      if (file.getName().endsWith(".txt") && file.isFile()) {
        System.out.println(file.getCanonicalPath());
      } else if (file.isDirectory()) {
        getAllTxtFile(file.getCanonicalPath());
      }
    }
  }

}
