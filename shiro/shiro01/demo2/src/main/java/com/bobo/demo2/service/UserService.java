package com.bobo.demo2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bobo.demo2.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

  public User getUserInfoByName(String name);

  List<String> getUserRoleInfo(String principal);

  List<String> getUserPermissionInfo(List<String> roles);
}
