package com.example;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusTestSpringbootApplicationTests {

  @Autowired
  private StudentMapper studentMapper;

  @Test
  public void testSelect() {
    List<Student> list = studentMapper.findAll();
    for (Student student : list) {
      System.out.println(student);
    }
  }

  


}
