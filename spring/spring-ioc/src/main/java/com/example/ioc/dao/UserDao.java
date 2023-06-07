package com.example.ioc.dao;


import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
  public void selectAll() {
    System.out.println("查看全部User");
  }
}
