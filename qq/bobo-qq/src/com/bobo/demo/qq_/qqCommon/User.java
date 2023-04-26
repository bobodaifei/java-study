package com.bobo.demo.qq_.qqCommon;

import java.io.Serializable;

import com.google.gson.Gson;

public class User implements Serializable{
  private String userId;
  private String pwd;
  private static final long SerializableUID = 1L;
  
  public User(String userId, String pwd) {
    this.userId = userId;
    this.pwd = pwd;
  }

  public User() {
    new Gson();
  }

  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getPwd() {
    return pwd;
  }
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  
}
