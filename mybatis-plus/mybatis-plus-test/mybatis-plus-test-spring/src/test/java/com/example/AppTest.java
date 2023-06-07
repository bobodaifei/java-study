package com.example;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AppTest {

  @Autowired
  private StudentMapper studentMapper;

  @Test
  public void testSelectList() {
    List<Student> students = studentMapper.selectList(null);
    for (Student student : students) {
      System.out.println(student);
    }
  }

}
