package com.bobo.bshop.exception;


import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.bobo.bshop.component.base.Result;
import com.bobo.publicInterceptor.exception.TokenAndTicketException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"com.bobo.bshop.controller"})
public class GlobalExceptionHandler {

  private static final Log log = LogFactory.get();

  @ExceptionHandler({TokenAndTicketException.class})
  @ResponseBody//返回json串
  public Result<?> customer1(HttpServletRequest request, TokenAndTicketException e) {
    return Result.error(e.getCode(), e.getMsg(), e.getData());
  }

  //统一异常处理@ExceptionHandler,主要用于Exception
  @ExceptionHandler({CustomException.class})
  @ResponseBody//返回json串
  public Result<?> customer(HttpServletRequest request, CustomException e) {
    return Result.error(e.getCode(), e.getMsg());
  }


  //统一异常处理@ExceptionHandler,主要用于Exception
  @ExceptionHandler(Exception.class)
  @ResponseBody//返回json串
  public Result<?> error(HttpServletRequest request, Exception e) {
    log.error("异常信息：", e);
    return Result.error("-1", "系统异常");
  }


}
