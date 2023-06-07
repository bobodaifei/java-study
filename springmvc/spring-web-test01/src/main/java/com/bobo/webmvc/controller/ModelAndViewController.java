package com.bobo.webmvc.controller;

import com.bobo.webmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/modelAndView")
public class ModelAndViewController {

  @GetMapping("/test")
  public ModelAndView test(ModelAndView modelAndView){
    User user = new User();
    user.setUserno("1123");
    modelAndView.addObject("user",user);
    modelAndView.setViewName("/index.jsp");
    return modelAndView;
  }

  @GetMapping("/test1")
  @ResponseBody
  public String test1(){
    //不能直接返回字符串，默认会返回视图名
    return "aoligei";
  }
}
