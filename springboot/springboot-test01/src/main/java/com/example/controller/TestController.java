package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Controller
@Validated
public class TestController {
  @GetMapping("/test/{path}")
  public String test(@MatrixVariable("low") Integer low,
                     @MatrixVariable("brand") List<String> brand,
                     @PathVariable("path") String path) {

    System.out.println(brand);
    System.out.println(low);
    return "aaa";
  }

  @GetMapping("/valid")
  public String valid(@NotNull String name, @NotNull @Max(100) Integer age) {
    System.out.println(name);
    return name;
  }

  @GetMapping("/valid1")
  public User valid1(@Valid User user) {
    return user;
  }

  @GetMapping("/boboMessageConverter")
  public User boboMessageConverter() {
    User user = new User();
    user.setAge(11);
    user.setName("zhangsan");
    int i = 1 / 0;
    return user;
  }

  @GetMapping("/mapp")
  public String mapp(Model model) {
    model.addAttribute("msg", "嘿嘿 你好");
    model.addAttribute("link", "www.baidu.com");
    return "success";
  }





  @ExceptionHandler({ConstraintViolationException.class})
  public String handleUserNotFoundException(ConstraintViolationException e) {
    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
    StringBuilder errorMsg = new StringBuilder();
    for (ConstraintViolation constraintViolation : constraintViolations) {
      System.out.println(constraintViolation.getMessage());
      System.out.println(constraintViolation.getPropertyPath());
      errorMsg.append(constraintViolation.getPropertyPath().toString().split("\\.")[1]).append(constraintViolation.getMessage()).append("    ");
    }
    return errorMsg.toString();
  }

  @ExceptionHandler({BindException.class})
  public String handleUserNotFoundException(BindException e) {
    StringBuilder errorMsg = new StringBuilder();
    List<FieldError> errors = e.getFieldErrors();
    for (FieldError error : errors) {
      errorMsg.append(error.getField()).append(error.getDefaultMessage());
    }
    return errorMsg.toString();
  }


}
