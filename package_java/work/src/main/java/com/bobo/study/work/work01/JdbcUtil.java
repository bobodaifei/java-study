package com.bobo.study.work.work01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class JdbcUtil {
  private static final String DRIVER = getValue("driver");
  private static final String URL = getValue("url");
  private static final String USERNAME = getValue("username");
  private static final String PASSWORD = getValue("password");
  

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
    try {
      return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
      System.err.println("a获得数据连接失败。" + e.getMessage());
    }
    return null;
  }

  // 关闭连接
  public static void close(Connection conn, Statement stmt, ResultSet rs) {
    try {
      // 关闭数据库的资源的顺序最好与使用的顺序相反
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
      if (conn != null) {
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
    // System.out.println(getValue("driver"));
    // System.out.println(getConnection());

  }
}
