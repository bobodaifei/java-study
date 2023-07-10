package com.bobo.sso.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.bobo.sso.pojo.dto.SsoDTO;
import com.bobo.sso.pojo.vo.UserVO;
import com.bobo.sso.service.SsoService;
import com.bobo.sso.service.UserService;
import com.bobo.sso.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SsoServiceImpl implements SsoService {

  @Autowired
  JedisUtil jedisUtil;

  @Autowired
  UserService userService;

  @Override
  public UserVO verify(SsoDTO dto) {
    String userId = jedisUtil.get(dto.getTicket());
    if (StrUtil.isEmpty(userId)){
      return null;
    }
    UserVO userVO = userService.selectById(Integer.valueOf(userId));
    userVO.setPassword(DigestUtil.md5Hex(userVO.getPassword()));
    return userVO;
  }
}
