package com.bobo.service;

import com.bobo.entity.User;

public interface UserService {
  public User login(User user);

  public User selectById(String user_no);
}
