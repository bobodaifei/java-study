package com.bobo.service;


import com.bobo.pojo.dto.UserDTO;
import com.bobo.pojo.vo.UserVO;

import java.math.BigDecimal;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */

public interface UserService {

  /**
   * 根据id获取  详情
   *
   * @param id 主键
   */
  public UserVO selectById(Integer id);

  /**
   * 登录
   *
   * @param dto 参数
   * @return
   */
  public UserVO login(UserDTO dto);

  int modifyBlance(Integer userId, BigDecimal bigDecimal);
}
