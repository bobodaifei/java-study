package com.bobo.webmvc.service.serviceImpl;


import com.bobo.webmvc.dao.UserDao;
import com.bobo.webmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserDao userdao;

  @Override
  public void selectAll() {
    userdao.selectAll();
  }
}
