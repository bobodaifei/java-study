package com.bobo.purview.exception;

public class CustomException extends RuntimeException {
  private String code;
  private String msg;

  public CustomException(){}

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