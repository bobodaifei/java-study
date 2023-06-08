package com.example;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusTestSpringbootApplicationTests {

  @Autowired
  private StudentMapper studentMapper;

  @Autowired
  private StudentService studentService;

  @Test
  public void testSelect() {
    Student student = new Student();
    student.setStudentNo("123");
    List<Student> students = studentService.list();
    System.out.println(students);
  }







}
