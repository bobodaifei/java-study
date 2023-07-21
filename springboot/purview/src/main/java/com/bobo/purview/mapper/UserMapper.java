package com.bobo.purview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.purview.entity.User;
import com.bobo.purview.pojo.query.UserPage;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

  @Select("SELECT u.*, r.`name` FROM `user` AS u LEFT JOIN role AS r ON u.role_id = r.role_id")
  public Page<User> selectPageWithRoleName(UserPage page, Object o);
}
