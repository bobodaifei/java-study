package com.example.demo03.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo03.entity.User;
import com.example.demo03.mapper.UserMapper;
import com.example.demo03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User getUserInfoByName(String username) {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("username", username);
    return userMapper.selectOne(wrapper);
  }


  @Override
  public List<String> getUserRoleInfo(String principal) {
    return userMapper.getUserRoleInfoMapper(principal);
  }

  @Override
  public List<String> getUserPermissionInfo(List<String> roles) {
    return userMapper.getUserPermissionInfoMapper(roles);
  }
}
