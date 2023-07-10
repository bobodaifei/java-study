package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Student;

import java.util.List;

public interface StudentService extends IService<Student> {

  public List<Student> findAll1();
}
