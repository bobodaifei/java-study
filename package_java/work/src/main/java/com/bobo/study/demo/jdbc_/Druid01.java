package com.bobo.study.demo.jdbc_;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class Druid01 {
  public static void main(String[] args) throws Exception {
    new Druid01().testDruid();
  }

  public void testDruid() throws Exception {
    // 1. 加入 Druid jar包
    // 2. 加入 配置文件 druid.properties , 将该文件拷贝项目的src目录
    // 3. 创建Properties对象, 读取配置文件
    Properties properties = new Properties();
    properties.load(new FileInputStream("src\\druid8.properties"));

    // 4. 创建一个指定参数的数据库连接池, Druid连接池
    DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

    long start = System.currentTimeMillis();
    for (int i = 0; i < 5000; i++) {
      Connection connection = dataSource.getConnection();
      // System.out.println("连接成功!");
      connection.close();
    }
    long end = System.currentTimeMillis();
    System.out.println("druid连接池 操作500000 耗时=" + (end - start));// 539

  }

}
