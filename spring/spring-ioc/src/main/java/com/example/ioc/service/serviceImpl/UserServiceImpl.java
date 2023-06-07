package com.example.ioc.service.serviceImpl;


import com.example.ioc.dao.UserDao;
import com.example.ioc.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserDao userdao;
  private String name;


  @Override
  public void selectAll() {
    userdao.selectAll();
  }

  public void setUserdao(UserDao userdao) {
    this.userdao = userdao;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "UserServiceImpl{" +
            "userdao=" + userdao +
            ", name='" + name + '\'' +
            '}';
  }
}
