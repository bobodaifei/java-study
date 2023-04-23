package com.bobo.Filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.bobo.common.Result;

import cn.hutool.core.util.StrUtil;

@WebFilter(filterName = "Z_LoginFilter", urlPatterns = "/*")
public class Z_LoginFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    PrintWriter out = response.getWriter();

    String requestURI = request.getRequestURI();
    String[] uris = { "^\\S*.html$", "^\\S*.js$" };
    for (String uri : uris) {
      if (requestURI.matches(uri)) {
        filterChain.doFilter(servletRequest, servletResponse);
        return;
      }
    }

    String method = request.getParameter("method");
    if ("login".equals(method)) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }
    Cookie[] cookies = request.getCookies();
    if (cookies == null || cookies.length == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "暂未登录")));
      return;
    }
    for (Cookie cookie : cookies) {
      if ("customer_no".equals(cookie.getName())) {
        filterChain.doFilter(servletRequest, servletResponse);
        return;
      }
    }
    out.write(JSON.toJSONString(Result.error("-1", "暂未登录")));
  }

  @Override
  public void destroy() {
    // TODO Auto-generated method stub

  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // TODO Auto-generated method stub

  }

}
