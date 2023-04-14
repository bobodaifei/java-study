package com.bobo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.bobo.entity.Student;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

public interface StudentMapper {

  @Select("select * from student where class_no = #{class_no}")
  @Results({
      @Result(id = true, column = "student_no", property = "studentNo"),
      @Result(column = "name", property = "name"),
      @Result(column = "mobile", property = "mobile"),
      @Result(column = "gender", property = "gender"),
      @Result(column = "age", property = "age"),
      @Result(column = "class_no", property = "classNo")
  })
  public List<Student> selectByClassIdZJ(@Param("class_no") String class_no);

  public List<Student> selectByClassId(@Param("class_no") String class_no);
  
}
