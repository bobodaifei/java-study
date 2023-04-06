package com.bobo.study.work.work08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Demo01 {

  public static List<User> userList = new ArrayList<>();
  public static String urlTxt = "E:\\新建文件夹 (2)\\user.txt";
  public static String url = "E:\\新建文件夹 (2)";

  private User user;

  static {
    // 为了测试，但会覆盖之前的操作
    createUserFile();
    File file = new File(urlTxt);
    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(file));
      String str = null;
      while ((str = bufferedReader.readLine()) != null) {
        User user = new User();
        String[] split = str.split("&");
        for (String userStr : split) {
          String[] split2 = userStr.split(":(?![\\\\])");
          System.out.println(split2[0]);
          System.out.println(split2[1]);
          if ("userNo".equals(split2[0])) {
            user.setUserNo(split2[1]);
          } else if ("name".equals(split2[0])) {
            user.setName(split2[1]);
          } else if ("password".equals(split2[0])) {
            user.setPassword(split2[1]);
          } else if ("header".equals(split2[0])) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 1; i < split2.length; i++) {
              stringBuffer.append(split2[i]);
              if (i == 1) {
                stringBuffer.append(":");
              }
            }
            user.setHeader(stringBuffer.toString());
          }
        }
        Demo01.userList.add(user);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        bufferedReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void createUserFile() {
    File file = new File(urlTxt);
    BufferedWriter bw = null;
    try {
      if (!file.exists()) {
        file.createNewFile();
      } else {
        bw = new BufferedWriter(new FileWriter(file));
        bw.write("userNo:U0001&name:张三&password:123456&header:D:\\java\\test\\胡桃.jpg");
        bw.newLine();
        bw.write("userNo:U0002&name:李四&password:123456&header:D:\\java\\test\\胡桃.jpg");
        bw.newLine();
        bw.write("userNo:U0003&name:王五&password:123456&header:D:\\java\\test\\胡桃.jpg");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        bw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    // String key = null;
    // boolean flag = true;
    // Demo01 demo01 = new Demo01();
    // while (demo01.login()) {
    //   Scanner scanner = new Scanner(System.in);
    //   while (flag) {
    //     System.out.println("1、修改当前用户信息 \n2、添加用户 \n3、删除用户 \n4、退出登录。");
    //     System.out.println("请选择");
    //     key = scanner.next();
    //     switch (key) {
    //       case "1":
    //         demo01.updateUser();
    //         break;
    //       case "2":
    //         demo01.addUser();
    //         break;
    //       case "3":
    //         demo01.deleteUser();
    //         break;
    //       case "4":
    //         flag = false;
    //         break;
    //       default:
    //         System.out.println("请重试");
    //         break;
    //     }
    //   }
    // }
  }

  public boolean login() {
    System.out.println("欢迎登陆xxx管理系统");
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入账号");
    String userNo = scanner.next();
    System.out.println("请输入密码");
    String pwd = scanner.next();
    Iterator<User> iterator = userList.iterator();
    while (iterator.hasNext()) {
      User next = iterator.next();
      if (next.getUserNo().equals(userNo) && next.getPassword().equals(pwd)) {
        System.out.println("欢迎" + next.getName() + "登录");
        this.user = next;
        return true;
      }
    }
    System.out.println("登陆失败");
    return false;
  }

  public void updateUser() {
    System.out.println("修改当前用户信息");
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入新账号");
    String newUserNo = scanner.next();
    System.out.println("请输入新密码");
    String newPwd = scanner.next();
    String oldUser = "userNo:" + user.getUserNo() + "&name:" + user.getName() + "&password:" + user.getPassword()
        + "&header:" + user.getHeader();
    String newUser = "userNo:" + newUserNo + "&name:" + user.getName() + "&password:" + newPwd + "&header:"
        + user.getHeader();

    File file = new File(urlTxt);
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(file));
      StringBuffer stringBuffer = new StringBuffer();
      String str = null;
      while ((str = bufferedReader.readLine()) != null) {
        if (!str.equals(oldUser)) {
          stringBuffer.append(str + "\n");
        }
      }
      bufferedWriter = new BufferedWriter(new FileWriter(file));
      bufferedWriter.write(stringBuffer.toString());
      bufferedWriter.write(newUser);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        bufferedWriter.close();
        bufferedReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void addUser() {
    System.out.println("添加用户");
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入账号");
    String newUserNo = scanner.next();
    System.out.println("请输入用户名");
    String newUserName = scanner.next();
    System.out.println("请输入密码");
    String newPwd = scanner.next();
    System.out.println("请输入头像路径");
    String newHeader = scanner.next();
    String newUser = "userNo:" + newUserNo + "&name:" + newUserName + "&password:" + newPwd + "&header:"
        + newHeader;

    // 新增用户信息
    File file = new File(urlTxt);
    BufferedWriter bufferedWriter = null;
    try {
      bufferedWriter = new BufferedWriter(new FileWriter(file, true));
      bufferedWriter.newLine();
      bufferedWriter.write(newUser);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        bufferedWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // 头像复制

    File headerFile = new File(newHeader);
    copyFile(newHeader, url + "\\" + headerFile.getName());

  }

  public static void copyFile(String fromFile, String toFile) {
    // 头像复制
    File headerFile = new File(fromFile);
    File headerToFile = new File(toFile);
    FileInputStream in = null;
    FileOutputStream out = null;
    try {
      in = new FileInputStream(headerFile);
      out = new FileOutputStream(headerToFile);
      int count = 0;
      byte[] bytes = new byte[1024];
      while ((count = in.read(bytes)) != -1) {
        out.write(bytes, 0, count);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        in.close();
        out.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public void deleteUser() {
    System.out.println("删除用户");
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入需要删除的账号");
    String newUserNo = scanner.next();
    String oldUser = "userNo:" + newUserNo;

    File file = new File(urlTxt);
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(file));
      StringBuffer stringBuffer = new StringBuffer();
      String str = null;
      while ((str = bufferedReader.readLine()) != null) {
        if (!str.startsWith(oldUser)) {
          stringBuffer.append(str + "\n");
        }
      }
      bufferedWriter = new BufferedWriter(new FileWriter(file));
      bufferedWriter.write(stringBuffer.toString());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        bufferedWriter.close();
        bufferedReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

class User {
  private String userNo;
  private String name;
  private String password;
  private String header;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  @Override
  public String toString() {
    return "User [userNo=" + userNo + ", name=" + name + ", password=" + password + ", header=" + header + "]";
  }

}
