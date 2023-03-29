package com.bobo.study.demo.jdbc_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc02 {
  public static void main(String[] args)
      throws SQLException, FileNotFoundException, IOException, ClassNotFoundException {
    // ResultSet
    // 通过Properties对象获取配置文件的信息

    Properties properties = new Properties();
    properties.load(new FileInputStream("src\\mysql.properties"));
    // 获取相关的值
    String user = properties.getProperty("user");
    String password = properties.getProperty("password");
    String driver = properties.getProperty("driver");
    String url = properties.getProperty("url");

    // 1. 注册驱动
    Class.forName(driver);// 建议写上

    // 2. 得到连接
    Connection connection = DriverManager.getConnection(url, user, password);

    // 3. 得到Statement
    Statement statement = connection.createStatement();
    // 4. 组织SqL
    String sql = "select * from table_name";
    ResultSet resultSet = statement.executeQuery(sql);

    // 5. 使用while取出数据
    while (resultSet.next()) { // 让光标向后移动，如果没有更多行，则返回false
      String id = resultSet.getString(1); // 获取该行的第1列
      // int id1 = resultSet.getInt("id"); 通过列名来获取值, 推荐
      String name = resultSet.getString(2);// 获取该行的第2列
      String sex = resultSet.getString(3);
      String date = resultSet.getString(4);

      System.out.println(id + "\t" + name + "\t" + sex + "\t" + date);
    }

    // 6. 关闭连接
    resultSet.close();
    statement.close();
    connection.close();

  }

}
