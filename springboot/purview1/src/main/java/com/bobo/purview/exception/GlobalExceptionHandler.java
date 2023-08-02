package com.bobo.purview.exception;


import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.bobo.purview.component.base.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.bobo.purview.controller")
public class GlobalExceptionHandler {

  private static final Log log = LogFactory.get();

  @ResponseBody
  @ExceptionHandler(UnauthorizedException.class)
  public Result<?> unauthorizedException(UnauthorizedException ex) {
    return Result.error(ex.getCode(), ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(AuthorizationException.class)
  public Result<?> authorizationException(AuthorizationException ex) {
    return Result.error(ex.getCode(), ex.getMessage());
  }

  //统一异常处理@ExceptionHandler,主要用于Exception
  @ExceptionHandler(CustomException.class)
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
