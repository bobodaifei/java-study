package com.bobo.study.work.work5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

public class HouseWork01 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("123");
    list.add("123");
    list.add("456");
    list.add("899");
    list.add("456");
    list.add("123");
    System.out.println(strExistList(list, "456"));

    // List<User> arrayList1 = new ArrayList<>();
    // ArrayList<User> arrayList2 = new ArrayList<>();
    // arrayList1.add(new User("123456"));
    // arrayList1.add(new User("234567"));
    // arrayList1.add(new User("345678"));
    // arrayList2.add(new User("234567"));
    // arrayList2.add(new User("345678"));
    // arrayList2.add(new User("456789"));
    // System.out.println(mergeList(arrayList1, arrayList2));

    // HashSet<User> hashSet = new HashSet<>();
    // User user = new User("222");
    // // hashSet.add(user);
    // hashSet.add(new User("222"));
    // hashSet.add(new User("333"));
    // hashSet.add(new User("444"));
    // System.out.println(joinSet(hashSet, user));

    // new HouseWork01().login();

    // List<Object> arrayList3 = new ArrayList<>();
    // arrayList3.add("123");
    // arrayList3.add("123");
    // arrayList3.add("123");
    // arrayList3.add("123");
    // arrayList3.add(123);
    // arrayList3.add(123);
    // arrayList3.add(123);
    // arrayList3.add(123.0);
    // removal(arrayList3);

    // listToMap("1001");

    // User user = User.userList.get(0);
    // user.setName("奥里给");
    // for (User u1 : User.userList) {
    //   System.out.println(u1);
    // }

  }

  // 1、封装一个方法，参数为 List<String> strList和String str
  // 判断strList里面是否存在str，如果存在，就从strList中剔除，并记录，结果返回“已提出全部str，共剔除xx处”
  public static String strExistList(List<String> strList, String str) {
    int count = 0;
    Iterator<String> each = strList.iterator();
    while (each.hasNext()) {
      if (each.next().equals(str)) {
        each.remove();
        count++;
      }
    }
    return "已提出全部" + str + ",共剔除" + count + "处";
  }

  // 2、封装一个方法，参数为：List<User>strList1和List<User>strList2
  // 要求两个List合并，并去除编号重复的对象，去重时保存strList2中的对象。
  public static List<User> mergeList(List<User> strList1, List<User> strList2) {
    Iterator<User> iterator = strList2.iterator();
    while (iterator.hasNext()) {
      User user1 = iterator.next();
      strList1.removeIf(new Predicate<User>() {
        @Override
        public boolean test(User user2) {
          if (user2.getUserNo().equals(user1.getUserNo())) {
            return true;
          }
          return false;
        }
      });
    }
    strList2.addAll(strList1);
    return strList2;
  }

  // 3、封装一个方法，参数为：Set<User>userSet和User
  // user,将user对象加入set集合，如果user对象重复，则替换掉set里面原本的对象，如果不重复，则判断user编号是否重复，如果重复同样替换掉。
  public static String joinSet(Set<User> userSet, User user) {
    boolean contains = userSet.contains(user);
    if (contains) {
      userSet.add(user);
      return "替换了重复user";
    }
    Iterator<User> iterator = userSet.iterator();
    while (iterator.hasNext()) {
      User user1 = iterator.next();
      if (user1.getUserNo().equals(user.getUserNo())) {
        iterator.remove();
        userSet.add(user);
        return "替换了同编号user";
      }
    }
    return "没有可替换的";
  }

  // 4、编写一个类，这个类里面有一个List<User> userList,
  // 将这个user对象放入他自己的list中，从user中获取list，再从list中获取user，这个user和那个user相等吗？
  // 相等 因为存入的是引用地址而不是新的对象

  // 1、定义一个user类，
  // 里面有三个属性 编号，名称，密码，定义一个user类的List，里面存储五个不重复的user对象。要求写个登录系统，
  // 运行程序的时候 会提示 请输入账号和密码，键盘输入账号和密码之后，如果输入的账号和密码和五个user对象中的一个相同，
  // 则控制台输出 user名称，并提示登录成功，反之就提示登录失败。
  public void login() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入账号");
    String id = scanner.next();
    System.out.println("请输入密码");
    String pwd = scanner.next();
    Iterator<User> iterator = User.userList.iterator();
    while (iterator.hasNext()) {
      User next = iterator.next();
      if (next.getUserNo().equals(id) && next.getPwd().equals(pwd)) {
        System.out.println(next.getName());
        return;
      }
    }
    System.out.println("登录失败");
  }

  // 2、封装一个方法，参数为List，其作用为List去重，并且控制台输出，去重操作完成，存在重复数据xxx个
  public static void removal(List list) {
    int count = 0;
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Object next = iterator.next();
      if (list.indexOf(next) != list.lastIndexOf(next)) {
        count++;
        iterator.remove();
      }
    }
    System.out.println("存在重复数据" + count + "个");
  }

  // 3、将第一题的userList转化为一个map 要求可以根据userNo 取出对应的user对象
  public static void listToMap(String userNo) {
    HashMap<String, User> hashMap = new HashMap<>();
    Iterator<User> iterator = User.userList.iterator();
    while (iterator.hasNext()) {
      User next = iterator.next();
      hashMap.put(next.getUserNo(), next);
    }
    User user = hashMap.get(userNo);
    System.out.println(user);
  }

  // 4、如果我将第三题的user对象的某个值改了，第一题的list里面对象的值会受到影响吗？

}

class User {
  private String userNo;
  private String name;
  private String pwd;
  static List<User> userList = new ArrayList<>();
  static {
    userList.add(new User("1001", "张三1", "123456"));
    userList.add(new User("1002", "张三2", "123456"));
    userList.add(new User("1003", "张三3", "123456"));
    userList.add(new User("1004", "张三4", "123456"));
    userList.add(new User("1005", "张三5", "123456"));
  }

  public User(String userNo) {
    this.userNo = userNo;
  }

  public User(String userNo, String name, String pwd) {
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
