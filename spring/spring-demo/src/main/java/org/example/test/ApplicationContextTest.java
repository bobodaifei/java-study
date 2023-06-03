package org.example.test;

import javax.sql.DataSource;

import org.example.service.ClassService;
import org.example.service.UserService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

public class ApplicationContextTest {
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//    ClassService classService = applicationContext.getBean(ClassService.class);
//    classService.update();
  }
}
