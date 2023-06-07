package com.bobo.dao;

import com.bobo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BasicDao<User>{
  public User login(User user) {
    String sql = "select * from user where user_no = ? and password = ?";
    return querySingle(sql, User.class, user.getUser_no(), user.getPassword());
  }

  public User selectById(String user_no) {
    String sql = "select * from user where user_no = ? ";
    return querySingle(sql, User.class, user_no);
  }
}
