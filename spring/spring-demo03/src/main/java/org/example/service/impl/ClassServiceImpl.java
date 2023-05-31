package org.example.service.impl;

import javax.annotation.Resource;

import org.example.mapper.ClassMapper;
import org.example.service.ClassService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component("classService")
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false)
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
  // System.out.println(userDaoList);
  // };

  @Override
  public void show() {
    // System.out.println(userDao03);
    System.out.println(classMapper.findAll());
  }

  @Override
  public void show1() {
    System.out.println(classMapper.findAll());
  }

  @Override
  @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false)
  public void update() {
    classMapper.update("cl0001", "奥里给");
    // int i = 1/0;
    classMapper.update("cl0001", "奥里给1");
  }

}
