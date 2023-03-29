package com.bobo.study.demo.jdbc_.demo.test;

import java.util.List;

import com.bobo.study.demo.jdbc_.demo.dao.UserDao;
import com.bobo.study.demo.jdbc_.demo.domain.User;

public class TestDao {

  public static void main(String[] args) {
    new TestDao().testUserDAO();
  }
  

  public void testUserDAO() {

    UserDao userDao = new UserDao();
    // 1. 查询
    List<User> users = userDao.queryMulti("SELECT * FROM `user` where user_id >= ?", User.class, 1000);
    System.out.println("===查询结果===");
    for (User user : users) {
      System.out.println(user);
    }

    // 2. 查询单行记录
    User user = userDao.querySingle("select * from `user` where user_id = ?", User.class, 1001);
    System.out.println("====查询单行结果====");
    System.out.println(user);

    // 3. 查询单行单列
    Object o = userDao.queryScalar("select user_name from `user` where user_id = ?", 1002);
    System.out.println("====查询单行单列值===");
    System.out.println(o);

    // 4. dml操作 insert ,update, delete
    int update = userDao.update("insert into `user` values(null, ?, ?)", "张无忌", "999");

    System.out.println(update > 0 ? "执行成功" : "执行没有影响表");

  }

}
