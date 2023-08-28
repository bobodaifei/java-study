package org.example.service;

import org.example.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

// @Component(value = "classService")
@Service
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
    new ClassServiceImpl().equals()
    // System.out.println(userDao03);
    new HashMap().put()
    System.out.println(classMapper.findAll());
  }

}
