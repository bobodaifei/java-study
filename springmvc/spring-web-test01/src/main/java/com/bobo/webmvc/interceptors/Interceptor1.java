package com.bobo.webmvc.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor1 implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      return true;
//    Whoops, looks your request is timing out. Our service has been growing quickly, and we are having some growing pains.We are working to add more capacity. Sorry for any inconvenience. Please try again a little later.
  }
}
