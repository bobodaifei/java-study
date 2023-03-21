package com.bobo.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "Filter02", urlPatterns = "/*")
public class Filter02 implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    servletRequest.setCharacterEncoding("utf-8");

    String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
    if (requestURI.contains("/login")) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }
    //
    HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
    // 判断身份验证
    if (session == null) {
      System.out.println("无session");
      // 404.html
    }
    // 放行
    filterChain.doFilter(servletRequest, servletResponse);

  }

}
