package com.bobo.Filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "Z_LoginFilter", urlPatterns = "/*" )
public class Z_LoginFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String requestURI = request.getRequestURI();
    String method = request.getParameter("method");
    if ("login".equals(method) || requestURI.contains("/login.jsp")) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }
    Object obj = request.getSession().getAttribute("customerNo");
    if (obj == null) {
      response.sendRedirect("/shopwork/login.jsp");
      return;
    }
    Cookie[] cookies = request.getCookies();
    if (cookies == null || cookies.length == 0) {
      response.sendRedirect("/shopwork/login.jsp");
      return;
    }
    for (Cookie cookie : cookies) {
      if ("customer_no".equals(cookie.getName())) {
        filterChain.doFilter(servletRequest, servletResponse);
        return;
      }
    }
    response.sendRedirect("/shopwork/login.jsp");
  }

  @Override
  public void destroy() {

  }
}