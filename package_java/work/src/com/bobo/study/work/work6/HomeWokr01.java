package com.bobo.study.work.work6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HomeWokr01 {
  static String str = "name:张三&no:U0001&password:123456#name:李四&no=U0002&password=123456";
  static List<User> users = new ArrayList<>();
  static {
    String[] split1 = str.split("#");
    for (String string : split1) {
      User user = new User();
      String[] split2 = string.split("&");
      for (String string2 : split2) {

        String[] split3 = string2.split("\\:|\\=");
        if (split3[0].equals("name")) {
          user.setName(split3[1]);
        }
        if (split3[0].equals("no")) {
          user.setNo(split3[1]);
        }
        if (split3[0].equals("password")) {
          user.setPassword(split3[1]);
        }
      }
      users.add(user);
    }

  }

  public static String login() {
    System.out.println("欢迎登录xxx管理系统请输入账号密码");
    while (true) {
      System.out.println("请输入账号");
      Scanner scanner = new Scanner(System.in);
      String no = scanner.next();
      System.out.println("请输入密码");
      String pwd = scanner.next();
      Iterator<User> iterator = users.iterator();
      while (iterator.hasNext()) {
        User next = iterator.next();
        if (no.equals(next.getNo()) && pwd.equals(next.getPassword())) {
          return "登录成功";
        }
      }
      System.out.println("不存在就输出账号密码不正确,请重新输入");
    }
  }

  public static void main(String[] args) {
    System.out.println(login());
  }

}

class User {
  private String name;
  private String no;
  private String password;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User [name=" + name + ", no=" + no + ", password=" + password + "]";
  }

}
