package com.bobo.sso.controller;

import com.bobo.sso.component.base.Result;
import com.bobo.sso.pojo.dto.UserDTO;
import com.bobo.sso.pojo.dto.SsoDTO;
import com.bobo.sso.pojo.vo.UserVO;
import com.bobo.sso.service.SsoService;
import com.bobo.sso.service.UserService;
import com.bobo.sso.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/sso")
public class SsoController extends BaseController {

  @Autowired
  UserService userService;

  @Autowired
  SsoService ssoService;

  @Autowired
  JedisUtil jedisUtil;

  @PostMapping("/login")
  public Result<?> login(@RequestBody UserDTO dto) {
    UserVO res = userService.login(dto);
    if (res == null) {
      return Result.error("-1", "登录失败");
    }
    return Result.success(res);
  }

  @PostMapping("/isLogin")
  public Result<?> isLogin() {
    UserVO userVO = getUser();
    UserDTO dto = new UserDTO();
    dto.setUserName(userVO.getUserName());
    dto.setPassword(userVO.getPassword());
    UserVO res = userService.login(dto);
    if (res == null) {
      return Result.error("-1", "登录失败");
    }
    return Result.success(res);
  }

  @PostMapping("/verify")
  public Result<?> verify(@RequestBody SsoDTO dto){
    UserVO userVO = ssoService.verify(dto);
    if (userVO==null){
      return Result.error("-1","ticket不存在");
    }
    System.out.println("true");

    return Result.success(userVO);
  }


}
