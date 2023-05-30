package org.example.service.impl;

import org.example.mapper.ClassMapper;
import org.example.service.ClassService;
import org.springframework.stereotype.Component;

// @MyComponent("ClassService")
public class ClassServiceImpl implements ClassService{
  private ClassMapper classMapper;

  public void setClassMapper(ClassMapper classMapper) {
    this.classMapper = classMapper;
  }

  @Override
  public void show() {
    System.out.println(classMapper.findAll());
  }
  @Override
  public void show1() {
    System.out.println(classMapper.findAll());
  }

  @Override
  public void update() {
    classMapper.update("cl0001", "奥里给");
    // System.out.println(userDao03);
    // int i = 1/0;
    classMapper.update("cl0001", "奥里给1");
  }
  
}
