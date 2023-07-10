package com.bobo.sso.mapper;

import com.bobo.sso.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
public interface UserMapper {

  public User selectById(@Param("id") Integer id);

  public User login(User entity);
}
