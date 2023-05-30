package org.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Many;

import org.apache.ibatis.annotations.Param;

import org.example.entity.Class_;

public interface ClassMapper {
  List<Class_> findAll();

  List<Class_> findIn(@Param("listT") List<String> list);

  List<Class_> selectByTeachId(String teacher_no);

  @Update("update class set class_name = #{class_name} where class_no = #{class_no}")
  void update(@Param("class_no") String class_no, @Param("class_name") String class_name);

  @Select("select * from class where leader_teacher = #{teacher_no}")
  @Results({
      @Result(column = "class_no", property = "classNo"),
      @Result(column = "class_name", property = "className"),
      @Result(column = "leader_teacher", property = "leaderTeacher"),
      @Result(property = "students", column = "class_no", many = @Many(select = "org.example.mapper.StudentMapper.selectByClassIdZJ"))
  })
  List<Class_> selectByTeachIdZJ(@Param("teacher_no") String teacher_no);

}
