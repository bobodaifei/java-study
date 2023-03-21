package com.example.tomcat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Servlet;

//表示为一个web应用
public class Context {
  private String appName;
  private Map<String, Servlet> urlPatternMapping = new HashMap<>();

  public Context(String appName) {
    this.appName = appName;
  }

  public void addUrlPatternMapping(String urlPattern,Servlet servlet) {
    urlPatternMapping.put(urlPattern, servlet);
  }

  public Servlet getUrlPatternMapping(String urlPattern) {
    return urlPatternMapping.get(urlPattern);
  }

}
