package com.bobo.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
  // 1.声明一个工厂对象
  private static SqlSessionFactory factory;
  // 2.在静态代码块中创建会话工厂
  static {
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    // 得到输入流
    try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");) {
      factory = builder.build(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 3.静态方法得到会话工厂
  public static SqlSessionFactory getSessionFactory() {
    return factory;
  }

  // 4.得到会话对象
  public static SqlSession getSession() {
    return factory.openSession();
  }
}
