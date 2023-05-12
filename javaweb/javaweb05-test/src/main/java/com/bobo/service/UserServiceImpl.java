package com.bobo.service;

import com.bobo.dao.UserDao;
import com.bobo.entity.User;

public class UserServiceImpl implements UserService{
  private UserDao userDao = new UserDao();

  @Override
  public User login(User user) {
    return userDao.login(user);
  }

  @Override
  public User selectById(String user_no) {
    return userDao.selectById(user_no);
  }

} 
