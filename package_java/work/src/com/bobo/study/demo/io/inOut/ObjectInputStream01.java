package com.bobo.study.demo.io.inOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputStream01 {
  public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
    // 处理流的对象流

    // 指定反序列文件
    String filePath = "E:/abc22.dat";
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));

    // 读取要和保存的顺序一致,否则会出现异常
    System.out.println(ois.readInt());
    System.out.println(ois.readBoolean());
    System.out.println(ois.readChar());
    System.out.println(ois.readDouble());
    System.out.println(ois.readUTF());
    Object obj = ois.readObject();
    System.out.println(obj.getClass());
    System.out.println(obj);

    // 此处调用dog方法需要向下转型
    Dog dog = (Dog) obj;
    System.out.println(dog.getName());

    ois.close();

  }
}

