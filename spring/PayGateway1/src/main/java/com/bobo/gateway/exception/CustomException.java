package com.bobo.gateway.exception;

public class CustomException extends Exception {
  private String code;
  private String msg;

  public CustomException(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}