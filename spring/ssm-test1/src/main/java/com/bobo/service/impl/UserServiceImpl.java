package com.bobo.service.impl;


import com.bobo.component.convert.UserConvert;
import com.bobo.entity.User;
import com.bobo.mapper.UserMapper;
import com.bobo.pojo.dto.UserDTO;
import com.bobo.pojo.vo.UserVO;
import com.bobo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


/**
 * @author bobodaifei
 * @since 2023-06-19
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  private final UserConvert INSTANCE = UserConvert.INSTANCE;

  /**
   * 根据id获取  详情
   *
   * @param id 主键
   */
  @Override
  @Transactional
  public UserVO selectById(Integer id) {
    User entity = userMapper.selectById(id);
    return INSTANCE.toVO(entity);
  }

  /**
   * 登录
   *
   * @param dto 参数
   * @return
   */
  @Override
  public UserVO login(UserDTO dto){
    User entity = INSTANCE.toEntity(dto);
    return INSTANCE.toVO(userMapper.login(entity));
  }

  @Override
  public int modifyBlance(Integer userId, BigDecimal balance) {
    User user = new User();
    user.setUserId(userId);
    user.setBalance(balance);
    return userMapper.modifyBlance(user);
  }
}
