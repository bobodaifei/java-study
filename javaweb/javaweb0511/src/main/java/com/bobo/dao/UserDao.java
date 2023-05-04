package com.bobo.dao;

import com.bobo.entity.User;

public class UserDao extends BasicDao<User>{
  public User login(User user) {
    String sql = "select * from user where user_no = ? and password = ?";
    return querySingle(sql, User.class, user.getUser_no(), user.getPassword());
  }
}
