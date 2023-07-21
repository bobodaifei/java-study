package com.bobo.purview.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bobo.purview.entity.User;
import com.bobo.purview.pojo.dto.UserDTO;
import com.bobo.purview.pojo.query.UserPage;
import com.bobo.purview.pojo.vo.UserVO;

public interface UserService extends IService<User> {

  public UserVO login(UserDTO userDTO);

  public boolean save(UserDTO userDTO);

  public boolean update(Integer userId, UserDTO userDTO);

  public Page<User> pageWithRoleName(UserPage page, Wrapper<User> queryWrapper);
}
