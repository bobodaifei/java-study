package com.bobo.purview.exception;

public class UnauthorizedException extends CustomException {

  public UnauthorizedException(String code, String msg) {
    super(code, msg);
  }

  public UnauthorizedException(){
    super("-1","无权限");
  }

}
