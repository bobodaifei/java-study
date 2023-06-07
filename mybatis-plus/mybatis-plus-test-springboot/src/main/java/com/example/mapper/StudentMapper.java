package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Student;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {
  List<Student> findAll();
}
