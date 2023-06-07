package com.bobo.service.impl;

import com.bobo.dao.UserDao;
import com.bobo.entity.User;
import com.bobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public User login(User user) {
    return userDao.login(user);
  }

  @Override
  public User selectById(String user_no) {
    return userDao.selectById(user_no);
  }

} 
