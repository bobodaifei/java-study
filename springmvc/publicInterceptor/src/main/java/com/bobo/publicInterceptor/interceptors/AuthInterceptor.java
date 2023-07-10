package com.bobo.publicInterceptor.interceptors;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.bobo.publicInterceptor.component.base.Result;
import com.bobo.publicInterceptor.exception.TokenAndTicketException;
import com.bobo.publicInterceptor.pojo.vo.UserVO;
import com.bobo.publicInterceptor.utils.SsoJedisUtil;
import com.bobo.publicInterceptor.utils.SsoJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

  private static String url = "http://127.0.0.1:8080/sso/verify";

  @Autowired
  SsoJedisUtil jedisUtil;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  HttpServletRequest request;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("Access-Token");
    String ticket = request.getParameter("ticket");
    return validateTokenAndTicket(token, ticket);
  }

  private boolean validateTokenAndTicket(String token, String ticket) {
    System.out.println(token);
    System.out.println(ticket + "--------------");
    if (StrUtil.isNotEmpty(ticket)) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("ticket", ticket);
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<String> requestEntity = new HttpEntity<>(jsonObject.toJSONString(), headers);
//      String resultStr = restTemplate.postForObject(url, requestEntity, String.class);
//      Result result = JSON.parseObject(resultStr, Result.class);
//      if ("-1".equals(result.getCode())) {
//        throw new TokenAndTicketException("401", "ticket有误", "http://127.0.0.1:9876/Login");
//      }
//      String jsonString = JSON.toJSONString(result.getData());
//      System.out.println(jsonString);
//      UserVO userVO = JSON.parseObject(jsonString, UserVO.class);
      ResponseEntity<Result<UserVO>> response = restTemplate.exchange(
              url,
              HttpMethod.POST,
              requestEntity,
              new ParameterizedTypeReference<Result<UserVO>>() {}
      );
      Result<UserVO> result = response.getBody();
      if ("-1".equals(result.getCode())) {
        throw new TokenAndTicketException("401", "ticket有误", "http://127.0.0.1:9876/Login");
      }
      UserVO userVO = result.getData();
      String token1 = SsoJwtUtil.getToken(userVO);
      request.setAttribute("token", token1);
      return true;
    }
    if (!SsoJwtUtil.verify(token)) {
      throw new TokenAndTicketException("401", "token无效", "http://127.0.0.1:9876/Login");
    }
    return true;
  }
}
