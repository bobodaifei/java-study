package com.example;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 */
public class App {
  @Test
  public void testStudentList() throws Exception {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    StudentMapper userMapper = sqlSession.getMapper(StudentMapper.class);
    List<Student> list = userMapper.findAll();
    for (Student student : list) {
      System.out.println(student);
    }
  }

  @Test
  public void testMPStudentList() throws Exception {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
//这里使用的是MP中的MybatisSqlSessionFactoryBuilder
    SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    StudentMapper userMapper = sqlSession.getMapper(StudentMapper.class);
// 可以调用BaseMapper中定义的方法
    List<Student> list = userMapper.selectList(null);
    for (Student student : list) {
      System.out.println(student);
    }
  }
}
