package com.bobo.study.demo.classLoader_;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassLoader02 {
  public static void main(String[] args) throws Exception {
    // 反射的优化
    // 1.Method和Field、Constructor对象都有setAccessible()方法
    // 2.setAccessible作用是启动和禁用访问安全检查的开关
    // 3．参数值为true表示反射的对象在使用时取消访问检查，提高反射的效率。
    // 参数值为false则表示反射的对象执行访问检查

    Class c1 = Class.forName("com.bobo.study.demo.classLoader_.Student01");

    Constructor constructor = c1.getConstructor();
    constructor.setAccessible(true);
    Student01 s1 = (Student01) constructor.newInstance();

    Method toString = c1.getDeclaredMethod("toString");// 无参
    toString.setAccessible(true);
    toString.invoke(s1);

    Field name = c1.getDeclaredField("name");
    name.setAccessible(true);
    // 将获取的字段赋值后设置到对象中
    name.set(s1, "bobo");

  }
}
