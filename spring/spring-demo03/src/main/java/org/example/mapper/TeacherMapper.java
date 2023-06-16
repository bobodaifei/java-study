package org.example.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Teacher;

import java.util.List;


public interface TeacherMapper {

  @Select("select * from teacher #{}")
  @Results({
      @Result(column = "teacher_no", property = "teacherNo"),
      @Result(column = "teacher_name", property = "teacherName"),
      @Result(column = "age", property = "age"),
      @Result(column = "gender", property = "gender"),
      @Result(column = "pwd", property = "pwd"),
      @Result(property = "class_", column = "teacher_no",  one = @One (select = "org.example.mapper.ClassMapper.selectByTeachIdZJ"))
  })
  public List<Teacher> selectAllTCSZJ();

  public List<Teacher> selectAllTCS();




}
