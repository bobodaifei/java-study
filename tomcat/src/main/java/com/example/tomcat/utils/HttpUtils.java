package com.example.tomcat.utils;

import com.example.tomcat.entity.Request;

public class HttpUtils {
  public static Request parseHeader(byte[] bytes) {
    //转字符串
    String httpString = new String(bytes);
    //获取请求行
    String requestLine = httpString.substring(0, httpString.indexOf("\r\n"));
    //请求行分割后传入Request
    String[] requestLineSplit = requestLine.split(" ");
    
    return new Request(requestLineSplit[0], requestLineSplit[1], requestLineSplit[2]);
  }
}
