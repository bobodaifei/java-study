package com.bobo.sso.service;


import com.bobo.sso.pojo.dto.UserDTO;
import com.bobo.sso.pojo.vo.UserVO;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */

public interface UserService {

  /**
   * 根据id获取  详情
   * @param id 主键
   */
  public UserVO selectById(Integer id);

  /**
   * 登录
   * @param dto 参数
   * @return
   */
  public UserVO login(UserDTO dto);


}
