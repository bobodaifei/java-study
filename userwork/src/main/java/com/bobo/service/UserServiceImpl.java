package com.bobo.service;

import com.bobo.dao.UserDao;
import com.bobo.entity.User;

public class UserServiceImpl implements UserService{
  private UserDao userDao = new UserDao();

  @Override
  public User login(User user) {
    String sql = "select * from user where user_no = ? and password = ?";
    return userDao.querySingle(sql, User.class, user.getUser_no(), user.getPassword());
  }

  
} 
