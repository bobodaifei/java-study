package com.bobo.study.demo.io.inOut;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectOutputStream01 {
  public static void main(String[] args) throws FileNotFoundException, IOException {
    // 保存后缀随意，序列化后保存格式不是文本，而是他的特定格式
    String filePath = "E:/abc22.dat";
    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
    outputStream.writeInt(100);
    outputStream.writeBoolean(false);
    outputStream.writeChar('a');
    outputStream.writeDouble(0.5);
    outputStream.writeUTF("bobo");

    //需要实现序列化Serializable
    outputStream.writeObject(new Dog("bobo's dog"));

    outputStream.close();
  }
}

class Dog implements Serializable{
  private String name;
  private static String age;
  // transient 短暂的
  private transient String color;
  // 3)序列化的类中建议添加SerialVersionUID.是序列化的版本号，为了提高版本的兼容性
  private static final long serialVersionUID = 1L;

  //会报错，需要Master进行序列化
  private Master master;
  
  public Dog(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Dog [name=" + name + "]";
  }

}
class Master{


}

// 序列化就是在保存数据时,保存数据的值和数据类型反序列化就是在恢复数据时，恢复数据的值和数据类型
// 需要让某个对象支持序列化机制，则必须让其类是可序列化的，为了让某个类是可序列化的，
// 该类必须实现如下两个接口之一:
// >Serializable1/这是一个标记接口/推荐
// >Externalizable//需要实现方法

//下面也是修饰器模式
// ObjectInputStream 提供序列化
// ObjectOutputStream 提供反序列化

// 1)读写顺序要一致
// 2)要求序列化或反序列化的对象,需要实现Serializable
// 3)序列化的类中建议添加SerialVersionUID.为了提高版本的兼容性（自定义版本）
// 4)序列化对象时，默认将里面所有属性都进行序列化，但除了static或transient修饰的成员
// 5)序列化对象时,要求里面属性的类型也需要实现序列化接口
// 6)序列化具备可继承性,也就是如果某类已经实现了序列化，则它的所有子类也已经默认实现了序列化
