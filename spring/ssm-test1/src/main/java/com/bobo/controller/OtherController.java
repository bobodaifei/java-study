package com.bobo.controller;

import cn.hutool.core.util.IdUtil;
import com.bobo.base.Result;
import com.bobo.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/other")
public class OtherController extends BaseController {

  @Autowired
  JedisUtil jedisUtil;

  @PostMapping("/getToken")
  public Result<?> getToken() {
    String token = IdUtil.getSnowflake().nextIdStr();
    Integer userId = getUser().getUserId();
    jedisUtil.set("orderToken" + ":" + userId, token);
    return Result.success(token);
  }


}
