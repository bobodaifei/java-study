package org.example.test;

import javax.sql.DataSource;

import org.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

public class ApplicationContextTest {
  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    DruidDataSource dataSource = applicationContext.getBean("dataSource", DruidDataSource.class);
    System.out.println(dataSource);

  }
}
