package com.bobo.study.work.work;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Work03 {
  public static void main(String[] args) throws Exception {
    String[] str = {"a","b"};
    execute("com.bobo.study.demo.work.Student01", "toString1", str);
  }
  public static Object execute(String className, String methodName, Object[] args) throws Exception{
    //获取className的Class类
    Class c1 = Class.forName(className);
    //获取构造器
    Constructor constructor = c1.getConstructor();
    //实例化
    Student01 s1 = (Student01)constructor.newInstance();
    // 
    Method toString = c1.getDeclaredMethod(methodName, Object[].class);
  Method[] methods = c1.getMethods();
  Method[] methods1 = c1.getDeclaredMethods();
    // 可变参数必须这样封装，因为java反射内部实现做了参数个数为1的判断，如果参数长度不为1，则会抛出异常
    Object[] cParams = { args };
    
    System.out.println(toString.invoke(s1, cParams));


    return toString.invoke(s1, cParams);
  }
}
