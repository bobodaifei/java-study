package org.example.service;

import java.util.List;

import javax.annotation.Resource;

import org.example.dao.UserDao;
import org.example.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// @Component(value = "classService")
@Component("classService")
public class ClassServiceImpl implements ClassService {

  @Value("${jdbc.url}")
  private String name;

  // @Value("${jdbc.url}")
  public void setName(String name) {
    this.name = name;
  }

  // @Autowired
  // @Qualifier("classMapper")
  @Resource
  private ClassMapper classMapper;

  // @Resource
  // @Autowired
  // @Qualifier("userDao")
  // private UserDao userDao03;

  // @Autowired
  // private void xxx(List<UserDao> userDaoList){
  //   System.out.println(userDaoList);
  // };

  @Override
  public void show() {
    // System.out.println(userDao03);
    System.out.println(classMapper.findAll());
  }

}
