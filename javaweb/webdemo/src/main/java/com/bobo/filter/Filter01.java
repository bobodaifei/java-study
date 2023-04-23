package com.bobo.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "Filter01", urlPatterns = "/*")
public class Filter01 implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    servletRequest.setCharacterEncoding("utf-8");
    filterChain.doFilter(servletRequest, servletResponse);
    // 1.通过拦截的请求对象得到请求包的参数信息，从而针对某些内容进行过滤
    String age = servletRequest.getParameter("age");
    // 2.根据被人，帮助http服务器判断本次请求是否合法
    if (Integer.valueOf(age) > 18) {
      // 将拦截请求对象和响应对象交还给tomcat，由tomcat继续调用资源文件
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      servletResponse.setContentType("text/html;charser=utf-8");
      PrintWriter writer = servletResponse.getWriter();
      writer.write("18xxx");
    }
  }

}
