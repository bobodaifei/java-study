package com.bobo.bshop.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.bobo.bshop.pojo.vo.UserVO;

import java.util.Calendar;

public class JwtUtil {


  // 把需要放入的数据放入到token
  public static String getToken(UserVO user) {

    // 设置超时时间
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.SECOND, 60000);

    // 创建JWT -- 使用JWT.create()方法创建
    // 此时builer对象中默认设置了header--标头，类型默认为JWT
    Builder builder = JWT.create();

    String token = builder// head
            .withAudience(user.getUserId().toString()) // 负载
            .withExpiresAt(instance.getTime()) // 超时时间

            .sign(Algorithm.HMAC256(user.getPassword()));// 签名（解析时要用）
    return token;
  }

}
