package com.bobo.study.demo.classLoader_;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ClassLoader03 {
  public static void main(String[] args) throws Exception {
    /*
     * 1. Class也是类，因此也继承Object类[类图]
     * 2. Class类对象不是new出来的，而是系统创建的,类加载器进行创建
     * 3.对于某个类的Class类对象，在内存中只有一份，因为类只加载一次
     * 4.每个类的实例都会记得自己是由哪个Class实例所生成
     * 5.通过Class可以完整地得到一个类的完整结构,通过一系列API
     * 6.Class对象是存放在堆的
     * 7.类的字节码二进制数据，是放在方法区的，有的地方称为类的元数据(包括方法代码,变量名，方法名，访问权限等等)https://www.zhihu.com/
     * question/38496907【示意图】
     */
    //获取student01类对应的class对象
    Class c1 = Class.forName("com.bobo.study.demo.classLoader_.Student01");

    //输出c1
    System.out.println(c1);//显示是哪个类的class对象
    System.out.println(c1.getClass());//输出c1运行类型

    //获取包名
    System.out.println(c1.getPackage().getName());//包名

    //得到类名
    System.out.println(c1.getName());

    //通过c1创建实例
    Constructor constructor = c1.getConstructor();
    Student01 s1 = (Student01) constructor.newInstance();

    //通过反射获取属性
    Field name = c1.getField("name");
    Field name1 = c1.getDeclaredField("name");

    //给属性赋值
    name.set(s1, "bobo");

    //获取所有属性
    Field[] fields = c1.getFields();
    Field[] declaredFields = c1.getDeclaredFields();
  }
}
