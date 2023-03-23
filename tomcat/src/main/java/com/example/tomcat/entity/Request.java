package com.example.tomcat.entity;

import java.net.Socket;

public class Request extends AbstractHttpServletRequest {
  private String method;
  private String url;
  private String protocol;//协议
  private Socket socket;

  public Request(String method, String url, String protocol) {
    this.method = method;
    this.url = url;
    this.protocol = protocol;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  @Override
  public StringBuffer getRequestURL() {
    return new StringBuffer(url);
  }

  @Override
  public String getMethod() {
    return method;
  }

  @Override
  public String getProtocol() {
    return protocol;
  }
  

  public Socket getSocket() {
    return this.socket;
  }

  @Override
  public String toString() {
    return "Request [method=" + method + ", url=" + url + ", protocol=" + protocol + "]";
  }

}
