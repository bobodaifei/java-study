package com.bobo.study.demo.jdbc_;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc06 {
  public void testDML() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = JdbcUtil.getConnection();

      String sql = "update actor1 set name = ? where number = ?";

      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, "小红");
      preparedStatement.setString(2, "2020200");

      int rows = preparedStatement.executeUpdate();
      System.out.println(rows > 0 ? "成功" : "失败");
    } catch (Exception e) {
      // TODO: handle exception
    } finally {
      JdbcUtil.close(connection, preparedStatement, null);
    }
  }

  // 查询
  public void testQuery() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = JdbcUtil.getConnection();
      String sql = "select name , sex from actor1 where number = ?";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, "2020200");
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString(1);
        String sex = resultSet.getString(2);
        System.out.println(name + "\t" + sex);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      JdbcUtil.close(connection, preparedStatement, resultSet);
    }

  }
}
