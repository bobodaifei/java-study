package com.example.dubboweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbointerface.service.StudentService;
import com.example.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

  @Reference(interfaceClass = StudentService.class, version = "2.0.0", check = false,timeout = 1000,retries = 2,loadbalance = "random",cluster = "failover")
  private StudentService studentService;

  @RequestMapping(value = "/student/count")
  public @ResponseBody Object studentCount() {
    Integer allStudentCount = studentService.queryAllStudentCount();
    return "学生总人数为：" + allStudentCount;
  }

  @RequestMapping(value = "/student/one")
  public @ResponseBody Object studentOne() throws InterruptedException {
    User allStudentCount = studentService.findUserById(1);
    new Object();
    return "学生总人数为：" + allStudentCount;
  }


}