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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.bobo.common.Result;
import com.bobo.entity.User;
import com.bobo.service.UserService;
import com.bobo.service.UserServiceImpl;

import cn.hutool.core.util.StrUtil;

@WebFilter(filterName = "Z_LoginFilter", urlPatterns = "/*")
public class Z_LoginFilter implements Filter {

  private UserService userService = new UserServiceImpl();

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
    
    String token = request.getHeader("Access-Token");
    if (StrUtil.isBlank(token)) {
      // throw new CustomException("401", "未获取到token, 请重新登录");
      out.write(JSON.toJSONString(Result.error("-1", "未获取到token, 请重新登录")));
      return;
    }

    String user_no = JWT.decode(token).getAudience().get(0);
    User user = userService.selectById(user_no);
    if (user == null) {
      out.write(JSON.toJSONString(Result.error("-1", "token不合法")));
      return;
    }

    // 验证 token
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
    try {
      jwtVerifier.verify(token);
    } catch (Exception e) {
      out.write(JSON.toJSONString(Result.error("-1", "token不合法")));
      return;
    }
    filterChain.doFilter(servletRequest, servletResponse);
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
