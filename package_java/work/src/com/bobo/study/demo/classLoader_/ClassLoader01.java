package com.bobo.study.demo.classLoader_;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassLoader01 {
  public static void main(String[] args) throws Exception {
    //比如可以用外部文件修改程序的走向


    // Student01 student01 = new Student01();
    Class c1 = Class.forName("com.bobo.study.demo.classLoader_.Student01");
    // Student01 s1 =new Student01();
    // Class<? extends Student01> c2 = s1.getClass();
    Class c3 = Student01.class;
    // System.out.println(c1==c3);

    // 获取公共属性
    Field[] fields = c1.getFields();// 只能获取公共属性s
    for (Field field : fields) {
      System.out.println(field.getName());// 获取字段名
      System.out.println(field.getModifiers());// 获取修饰符
      System.out.println(field.getType());// 获取类型
    }
    System.out.println("-------------------------");
    Field field1 = c1.getField("name");// 只能获取公共属性
    System.out.println(field1.getName());// 获取字段名
    System.out.println(field1.getModifiers());// 获取修饰符
    System.out.println(field1.getType());// 获取类型
    System.out.println("-------------------------");

    Field[] declaredFields = c1.getDeclaredFields();// 所有字段
    for (Field field2 : declaredFields) {
      System.out.println(field2.getName());// 获取字段名
      System.out.println(field2.getModifiers());// 获取修饰符
      System.out.println(field2.getType());// 获取类型
    }
    System.out.println("-------------------------");

    // 获取构造方法
    Constructor constructor = c1.getConstructor();
    Constructor constructor1 = c1.getConstructor(String.class, int.class);

    // 获取构造方法后就可以new对象
    Object obj = constructor.newInstance();
    System.out.println(obj instanceof Student01);
    // newInstance要根据constructor的构造器类型进行赋值
    Student01 s1 = (Student01) constructor.newInstance();
    
    ClassLoader classLoader = Student01.class.getClassLoader(); 
    Class loadClass = classLoader.loadClass("com.bobo.study.demo.classLoader_.Student01");


    // 意义不大，主使用是要用反射获取方法名再进行调用
    System.out.println(s1.getName());
    // 弃用
    // Object newInstance = c1.newInstance();
    System.out.println("-------------------------");

    // 利用反射为对象字段赋值
    // 1.获取字段
    Field name = c1.getDeclaredField("name");
    // 将获取的字段赋值后设置到对象中
    name.set(s1, "bobo");
    // 2.获取字段信息,获取某个对象所相应字段的值
    Object object = name.get(s1);
    System.out.println(object);
    System.out.println("-------------------------");

    // 利用反射调用对象方法
    // 可以视为把方法当作一个对象
    Method toString = c1.getDeclaredMethod("toString");// 无参
    // System.out.println(toString.getModifiers());//获取修饰符
    // System.out.println(toString.getReturnType().getName());//获取返回类型
    // 调用方法对象的方法
    toString.invoke(s1);
    Method toString1 = c1.getDeclaredMethod("toString", String.class);// 有参
    toString1.invoke(s1, "bobo1");



  }
}
// 如果要主动使用某个类时，
// 如果该类还未被加载到内存中，则jvm会通过加载、链接、初始化来对该类进行初始化
// 如果没有意外 jvm会连续完成三个步骤 统称为类加载或类初始化

// 1.加载
// 将类的class文件读入内存中，创建一个class类型的对象(唯一)，程序中使用任何类时，都会建立一个class类型的对象
// 由类加载器完成
// 2.链接 （验证 准备 解析）
// 2.1验证 比如语法错误、符合要求
// 2.2准备 负责静态变量分配内存以及其默认值
// 3.2解析
// 3.初始化 为类的静态变量赋予正确的初始值

// 类的加载时间
// 1.new 访问静态变量 访问静态方法 反射 初始化类的子类 jvm启动时标明的启动类，即文件名和类名相同的那个类

// 什么是反射
// Java反射说的是在运行状态中，对于任何一个类，我们都能够知道这个类有哪些方法和属性。
// 对于任何一个对象，我们都能够对它的方法和属性进行调用。
// 我们把这种动态获取对象信息和调用对象方法的功能称之为反射机制。

//
