package com.bobo.purview.exception;

public class AuthorizationException extends CustomException {

  public AuthorizationException(String code, String msg) {
    super(code, msg);
  }

  public AuthorizationException(){
    super("-1","权限认证失败");
  }

}