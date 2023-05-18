package org.example.service;

import org.example.dao.UserDao;

public class UserServiceImpl implements UserService{
  //BeanFactory去调用此方法 从容器中获得userDao并设置到此处
  public void setUserDao(UserDao UserDao) {
    System.out.println("userDao被设置了");
  }
}
 