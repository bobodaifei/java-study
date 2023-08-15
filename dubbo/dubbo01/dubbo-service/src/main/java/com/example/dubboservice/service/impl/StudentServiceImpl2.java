package com.example.dubboservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbointerface.service.StudentService;
import com.example.entity.User;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = StudentService.class, version = "2.0.0", timeout = 1000,retries = 2,weight = 200)
public class StudentServiceImpl2 implements StudentService {
  @Override
  public Integer queryAllStudentCount() {
    return 6662;
  }

  int i =1;
  @Override
  public User findUserById(int id) {

    System.out.println("服务被调用了:"+i+++"次");
    //查询User对象
    User user = new User(1, "zhangsan", "123");
    //假如数据库查询很慢，查了5秒
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return user;
  }
}