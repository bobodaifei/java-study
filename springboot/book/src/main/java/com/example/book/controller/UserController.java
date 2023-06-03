package com.example.book.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.entity.Body;
import com.example.book.entity.ResponseBean;

@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping("/login")
  public ResponseBean login() {
  ResponseBean result = new ResponseBean("200", "请求成功，方法：testGet");
  return result;
  }
  // @GetMapping("/login01")
  // public ResponseBean login01(@RequestParam("username") String username) {
  // ResponseBean result = new ResponseBean("200", "请求成功，方法：testGet" + username);
  // return result;
  // }

  // @GetMapping("/login02/{username}")
  // public ResponseBean login02(@PathVariable("username") String username) {
  // ResponseBean result = new ResponseBean("200", "请求成功，方法：testGet" + username);
  // return result;
  // }

  // @PostMapping("/login")
  // public ResponseBean login(@RequestParam("username") String username) {
  //   ResponseBean result = new ResponseBean("200", "请求成功，方法：testPost" + username);
  //   return result;
  // }
  // @PostMapping("/login01")
  // public ResponseBean login01(ResponseBean responseBean) {
  //   ResponseBean result = new ResponseBean("200", "请求成功，方法：testPost" + responseBean.getCode());
  //   return result;
  // }
  // @PostMapping("/login02")
  // public ResponseBean login02(ResponseBean responseBean) {
  //   ResponseBean result = new ResponseBean("200", "请求成功，方法：testPost11" + responseBean.getCode());
  //   return result;
  // }
  // @PostMapping("/login03")
  // public ResponseBean login03(@RequestBody ResponseBean responseBean) {
  //   ResponseBean result = new ResponseBean("200", "请求成功，方法：testPost11" + responseBean.getCode());
  //   return result;
  // }

  // @PostMapping("/login04")
  // public String login04(@RequestBody ResponseBean responseBean) {
  //   System.out.println(responseBean);
  //   return "redirect:index.html";
  // }
  // @PutMapping("/login01")
  // public void login01(@RequestBody ResponseBean responseBean) {
  //   ResponseBean result = new ResponseBean("200", "请求成功，方法：testPost11" + responseBean.getCode());
  //   System.out.println(result);
  // }
  @DeleteMapping("/login01")
  public void login01() {
    
    System.out.println("result");
  }

  // @InitBinder("r")
  // private void initBinder(WebDataBinder binder) {
  //   binder.setFieldDefaultPrefix("r.");
  // }
  // @InitBinder("b")
  // private void initBinder1(WebDataBinder binder) {
  //   binder.setFieldDefaultPrefix("b.");
  // }

}
