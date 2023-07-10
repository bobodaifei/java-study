package com.bobo.sso.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.bobo.sso.component.convert.UserConvert;
import com.bobo.sso.entity.User;
import com.bobo.sso.mapper.UserMapper;
import com.bobo.sso.pojo.dto.UserDTO;
import com.bobo.sso.pojo.vo.UserVO;
import com.bobo.sso.service.UserService;
import com.bobo.sso.utils.JedisUtil;
import com.bobo.sso.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author bobodaifei
 * @since 2023-06-19
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Autowired
  JedisUtil jedisUtil;

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
  public UserVO login(UserDTO dto) {
    User entity = INSTANCE.toEntity(dto);
    UserVO userVO = INSTANCE.toVO(userMapper.login(entity));
    if (userVO == null) {
      return null;
    }

    String ticket = RandomUtil.randomString(20);
    userVO.setTicket(ticket);
    jedisUtil.set(ticket, String.valueOf(userVO.getUserId()));
    jedisUtil.expire(ticket,5);

    String token = JwtUtil.getToken(userVO);
    userVO.setToken(token);

    userVO.setPassword(DigestUtil.md5Hex(userVO.getPassword()));
    return userVO;
  }

}
