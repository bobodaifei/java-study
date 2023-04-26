package com.bobo.service;

import com.bobo.dao.UserDao;
import com.bobo.entity.User;
import com.bobo.utils.JdbcUtil;

public class UserServiceImpl implements UserService{
  private UserDao userDao = new UserDao();

  @Override
  public User login(User user) {
    User res = userDao.login(user);
    JdbcUtil.close();
    return res;
  }

  
} 
