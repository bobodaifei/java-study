package com.bobo.controller;


import com.bobo.aop.EnableLog;
import com.bobo.base.Result;
import com.bobo.pojo.dto.UserDTO;
import com.bobo.pojo.vo.UserVO;
import com.bobo.service.UserService;
import com.bobo.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
@Api(value = " API", tags = "")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @ApiOperation("登录")
  @PostMapping("/login")
  @EnableLog("登录 ")
  public Result<?> login(@RequestBody UserDTO dto) {
    UserVO user = userService.login(dto);
    if (user == null) {
      return Result.error("-1", "账号密码无效");
    }
    String token = JwtUtil.getToken(user);
    user.setToken(token);
    return Result.success(user);
  }
}
