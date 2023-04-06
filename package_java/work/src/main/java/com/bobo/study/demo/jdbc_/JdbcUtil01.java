package com.bobo.study.demo.jdbc_;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class JdbcUtil01 {
  private static final String DRIVER = getValue("driver");
  private static final String URL = getValue("url");
  private static final String USERNAME = getValue("username");
  private static final String PASSWORD = getValue("password");
  
  static ConcurrentHashMap<Connection, Boolean> map = new ConcurrentHashMap<>();
  static final int INIT_NUM = 20;
  static final int GROW_NUM = 10;

  static double critical = 0.75;
  static int total = 0;
  static int useTotal = 0;

  static {
    try {
      // 注册数据库驱动程序
      Class.forName(DRIVER);
      grow(INIT_NUM);
    } catch (Exception e) {
      // System.err.println("注册数据库驱动程序失败。" + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  //连接扩容
  public static void grow(int num) {
    try {
      for (int i = 0; i < num; i++) {
        map.put(DriverManager.getConnection(URL, USERNAME, PASSWORD), true);
        total++;
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  //

  // 连接数据库
  public static Connection getConnection() {
    if (useTotal> (int)(total*0.75)) {
      grow(GROW_NUM);
    }
    Iterator<Entry<Connection, Boolean>> iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<Connection, Boolean> next = iterator.next();
      if (next.getValue()) {
        next.setValue(false);
        useTotal++;
        return next.getKey();
      }
    }
    return getConnection();
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
      Iterator<Entry<Connection, Boolean>> iterator = map.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<Connection, Boolean> next = iterator.next();
        if (next.getKey()== conn) {
          next.setValue(true);
        }
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
