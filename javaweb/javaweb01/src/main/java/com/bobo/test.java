package com.bobo;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bobo.entity.Teacher;
import com.bobo.mapper.TeacherMapper;
import com.bobo.utils.MybatisUtils;

public class test {
  public static <T> void main(String[] args) {
    // 1.通过工具类得到会话对象
    SqlSession session = MybatisUtils.getSession();
    // 2.会话对象的得到UserMapper接口代理对象
    // ClassMapper classMapper = session.getMapper(ClassMapper.class);
    TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
    TeacherMapper teacherMapper2 = session.getMapper(TeacherMapper.class);
    // 3.生成了代理对象
    System.out.println(teacherMapper);
    // 4.执行查询操作
    // List<Class_> class_ = classMapper.findAll();
    // List<String> list = new ArrayList<>();
    // list.add("tc0001");
    // list.add("tc0002");
    // List<Class_> class_ = classMapper.findIn(list);
    List<Teacher> res = teacherMapper.selectAllTCSZJ();

    //
    List<Teacher> res2 = teacherMapper2.selectAllTCSZJ();

    // 5.遍历
    res.stream().forEach(System.out::println);
    res2.stream().forEach(System.out::println);
    // 6.关闭会话
    session.close();
  }

}
