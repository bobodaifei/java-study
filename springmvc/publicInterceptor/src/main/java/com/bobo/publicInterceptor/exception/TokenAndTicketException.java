package com.bobo.publicInterceptor.exception;

public class TokenAndTicketException extends RuntimeException {
  private String code;
  private String msg;

  private String data;

  public TokenAndTicketException(String code, String msg, String data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public String getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public String getData() {
    return data;
  }
}