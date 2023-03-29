package com.bobo.study.demo.jdbc_;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jdbc07 {
  // 没有使用事务.
  public void noTransaction() {

    // 操作转账的业务
    // 1. 得到连接
    Connection connection = null;
    // 2. 组织一个sql
    String sql = "update account set balance = balance - 100 where id = 1";
    String sql2 = "update account set balance = balance + 100 where id = 2";
    PreparedStatement preparedStatement = null;
    // 3. 创建PreparedStatement 对象
    try {
      connection = JdbcUtil.getConnection(); // 在默认情况下，connection是默认自动提交
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.executeUpdate(); // 执行第1条sql

      int i = 1 / 0; // 模拟异常
      preparedStatement = connection.prepareStatement(sql2);
      preparedStatement.executeUpdate(); // 执行第3条sql

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // 关闭资源
      JdbcUtil.close(connection, preparedStatement, null);
    }
  }

  // 事务来解决
  public void useTransaction() {

    // 操作转账的业务
    // 1. 得到连接
    Connection connection = null;
    // 2. 组织一个sql
    String sql = "update account set balance = balance - 100 where id = 1";
    String sql2 = "update account set balance = balance + 100 where id = 2";
    PreparedStatement preparedStatement = null;
    // 3. 创建PreparedStatement 对象
    try {
      connection = JdbcUtil.getConnection(); // 在默认情况下，connection是默认自动提交
      // 将 connection 设置为不自动提交
      connection.setAutoCommit(false); // 开启了事务
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.executeUpdate(); // 执行第1条sql

      int i = 1 / 0; // 模拟异常
      preparedStatement = connection.prepareStatement(sql2);
      preparedStatement.executeUpdate(); // 执行第3条sql

      // 这里提交事务
      connection.commit();

    } catch (Exception e) {
      // 这里我们可以进行回滚，即撤销执行的SQL
      // 默认回滚到事务开始的状态.
      System.out.println("执行发生了异常，撤销执行的sql");
      try {
        connection.rollback();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      e.printStackTrace();
    } finally {
      // 关闭资源
      JdbcUtil.close(connection, preparedStatement, null);
    }
  }
}
