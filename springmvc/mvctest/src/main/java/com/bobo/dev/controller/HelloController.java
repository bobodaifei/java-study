package com.bobo.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class HelloController {
  // 表示请url请求到hello,生成视图对象mv,mv对象要处理的jsp文件为hello.jsp
  // @GetMapping
  @RequestMapping("/hello")
  public ModelAndView helloWorld() {
    ModelAndView mv = new ModelAndView("index");
    mv.addObject("name", "123");
    return mv;
  }
}