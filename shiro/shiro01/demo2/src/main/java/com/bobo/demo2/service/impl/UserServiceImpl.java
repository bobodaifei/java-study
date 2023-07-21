package com.bobo.demo2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.demo2.entity.User;
import com.bobo.demo2.mapper.UserMapper;
import com.bobo.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User getUserInfoByName(String name) {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("name", name);
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
