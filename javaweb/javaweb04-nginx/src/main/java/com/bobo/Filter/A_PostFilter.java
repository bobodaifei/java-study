package com.bobo.Filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "A_PostFilter", urlPatterns = "/*")
public class A_PostFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    servletRequest.setCharacterEncoding("utf-8");
    servletResponse.setCharacterEncoding("utf-8");

    HttpServletResponse response = (HttpServletResponse) servletResponse;
    /* 允许跨域的主机地址 */
    response.setHeader("Access-Control-Allow-Origin", "*");
    /* 允许跨域的请求方法GET, POST, HEAD 等 */
    response.setHeader("Access-Control-Allow-Methods", "*");
    /* 重新预检验跨域的缓存时间 (s) */
    response.setHeader("Access-Control-Max-Age", "3600");
    /* 允许跨域的请求头 */
    response.setHeader("Access-Control-Allow-Headers", "*");
    /* 是否携带cookie */
    response.setHeader("Access-Control-Allow-Credentials", "true");


    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String requestURI = request.getRequestURI();
    String[] uris = { "^\\S*.html$", "^\\S*.js$" };
    for (String uri : uris) {
      if (requestURI.matches(uri)) {
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
        return;
      }
    }
    servletResponse.setContentType("application/json;charset=utf-8");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}