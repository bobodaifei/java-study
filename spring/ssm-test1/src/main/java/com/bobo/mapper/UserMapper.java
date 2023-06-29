package com.bobo.mapper;

import com.bobo.entity.User;
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
