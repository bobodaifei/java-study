package com.bobo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class JdbcUtil {
  private static final String DRIVER = getValue("driver");
  private static final String URL = getValue("url");
  private static final String USERNAME = getValue("username");
  private static final String PASSWORD = getValue("password");

  private static Map<Long,Connection> map = new ConcurrentHashMap<>();
  
  static {
    try {
      // 注册数据库驱动程序
      Class.forName(DRIVER);
    } catch (ClassNotFoundException e) {
      // System.err.println("注册数据库驱动程序失败。" + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  // 连接数据库
  public static Connection getConnection() {
    
    long id = Thread.currentThread().getId();
    try {
      Connection connection = map.get(id);
      if (connection==null) {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        map.put(id, connection);
      }
      return connection;
    } catch (SQLException e) {
      System.err.println("获得数据连接失败。" + e.getMessage());
    }
    return null;
  }

  // 开启事务
  public static void begin() {
    Connection connection = getConnection();
    try {
      connection.setAutoCommit(false);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 提交事务
  public static void commit() {
    // 所有连接都是通过ThreadLocal获得，连接是同一个连接
    Connection connection = null; // 防止在此处出现异常
    try {
      connection = getConnection();
      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 回滚事务、
  public static void rollback() {
    Connection connection = null;
    try {
      connection = getConnection();
      connection.rollback();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 关闭连接
  public static void close() {
    try {
      Connection conn = null;
      if ((conn=map.get(Thread.currentThread().getId())) != null) {
        map.remove(Thread.currentThread().getId());
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 关闭连接
  public static void close(ResultSet rs, Statement stmt, Connection conn) {
    try {
      // 关闭数据库的资源的顺序最好与使用的顺序相反
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
      if (conn != null) {
        map.remove(Thread.currentThread().getId());
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * 读取属性文件中的信息
   *
   * @param key
   * @return
   */
  private static String getValue(String key) {
    // 资源包绑定
    // ResourceBundle与Properties的区别在于
    // ResourceBundle通常是用于国际化的属性配置文件读取，Properties则是一般的属性配置文件读取。
    ResourceBundle bundle = ResourceBundle.getBundle("db");
    return bundle.getString(key);
  }

  public static void main(String[] args) {
     System.out.println(getValue("driver"));
     System.out.println(getConnection());

  }
}
