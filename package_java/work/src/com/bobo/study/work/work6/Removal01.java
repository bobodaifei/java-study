package com.bobo.study.work.work6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Removal01 {
  public static void main(String[] args) {
    ArrayList<User01> userList = new ArrayList<>();
    User01 user = new User01("1001", "张三1", "123456");
    userList.add(user);
    userList.add(user);
    userList.add(user);
    userList.add(new User01("1003", "张三3", "123456"));
    userList.add(new User01("1004", "张三4", "123456"));
    userList.add(new User01("1005", "张三5", "123456"));
    removal(userList);
    
  }


  public static void removal(List<User01> list) {
    int count = 0;
    Iterator<User01> iterator = list.iterator();
    while (iterator.hasNext()) {
      User01 next = iterator.next();
      if (list.indexOf(next) != list.lastIndexOf(next)) {
        count++;
        iterator.remove();
      }
    }
    System.out.println("存在重复数据" + count + "个");
  }
}

class User01 {
  private String userNo;
  private String name;
  private String pwd;

  public User01(String userNo, String name, String pwd) {
    this.userNo = userNo;
    this.name = name;
    this.pwd = pwd;
  }

  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  @Override
  public String toString() {
    return "User [userNo=" + userNo + ", name=" + name + ", pwd=" + pwd + "]";
  }

}
