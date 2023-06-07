package com.bobo.dao;

import com.bobo.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@Repository
public class BasicDao<T> {

  private QueryRunner qr = new QueryRunner();

  // 开发通用的dml方法, 针对任意的表
  public int update(String sql, Object... parameters) {

    Connection connection = null;

    try {
      connection = JdbcUtil.getConnection();
      int update = qr.update(connection, sql, parameters);
      return update;
    } catch (SQLException e) {
      throw new RuntimeException(e); // 将编译异常->运行异常 ,抛出
    } finally {
      // JdbcUtil.close(null, null, connection);
    }

  }

  // 返回多个对象(即查询的结果是多行), 针对任意表

  /**
   *
   * @param sql        sql 语句，可以有 ?
   * @param clazz      传入一个类的Class对象 比如 Actor.class
   * @param parameters 传入 ? 的具体的值，可以是多个
   * @return 根据Actor.class 返回对应的 ArrayList 集合
   */
  public <E> List<E> queryMulti(String sql, Class<E> clazz, Object... parameters) {

    Connection connection = null;
    try {
      connection = JdbcUtil.getConnection();
      return qr.query(connection, sql, new BeanListHandler<E>(clazz), parameters);

    } catch (SQLException e) {
      throw new RuntimeException(e); // 将编译异常->运行异常 ,抛出
    } finally {
      // JdbcUtil.close(null, null, connection);
    }

  }

  //查询单列数据通用方法
  public <E> List<E> queryColumn(String sql, String columnName, Object... parameters) {

    Connection connection = null;
    try {
      connection = JdbcUtil.getConnection();
      return qr.query(connection, sql, new ColumnListHandler<E>(columnName), parameters);
    } catch (SQLException e) {
      throw new RuntimeException(e); // 将编译异常->运行异常 ,抛出
    } finally {
      // JdbcUtil.close(null, null, connection);
    }

  }

  // 查询单行结果 的通用方法
  public <E> E querySingle(String sql, Class<E> clazz, Object... parameters) {

    Connection connection = null;
    try {
      connection = JdbcUtil.getConnection();
      return qr.query(connection, sql, new BeanHandler<E>(clazz), parameters);

    } catch (SQLException e) {
      throw new RuntimeException(e); // 将编译异常->运行异常 ,抛出
    } finally {
      // JdbcUtil.close(null, null, connection);
    }
  }

  // 查询单行单列的方法,即返回单值的方法

  public Object queryScalar(String sql, Object... parameters) {

    Connection connection = null;
    try {
      connection = JdbcUtil.getConnection();
      return qr.query(connection, sql, new ScalarHandler(), parameters);

    } catch (SQLException e) {
      throw new RuntimeException(e); // 将编译异常->运行异常 ,抛出
    } finally {
      // JdbcUtil.close(null, null, connection);
    }
  }

}
