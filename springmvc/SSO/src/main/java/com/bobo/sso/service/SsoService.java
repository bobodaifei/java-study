package com.bobo.sso.service;

import com.bobo.sso.pojo.dto.SsoDTO;
import com.bobo.sso.pojo.vo.UserVO;

public interface SsoService {
  public UserVO verify(SsoDTO dto);
}
