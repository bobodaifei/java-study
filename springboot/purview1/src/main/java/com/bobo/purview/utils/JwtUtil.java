package com.bobo.purview.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
  /**
   * token过期时间
   */
  private static final long EXPIRE_TIME = 30 * 60 * 1000;
  /**
   * token秘钥
   */
  private static final String KEY = "ASDJOIASJDOA";

  /**
   * 生成签名，30分钟过期
   *
   * @param username  用户名
   * @param password  密码
   * @param deviceId  设备ID
   * @return
   */
  public static String sign(String userId, String username, String password, String deviceId) {
    // 设置过期时间
    Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
    String token = null;
    try {
      JWTCreator.Builder builder = JWT.create();
      token = builder
              .withClaim("userId", userId)
              .withClaim("userName", username)
              .withClaim("password", password)
              .withClaim("deviceId", deviceId)
              .withExpiresAt(date)
              .sign(Algorithm.HMAC256(KEY));
    } catch (Exception e) {
      return null;
    }
    return token;
  }

  public static String sign(Integer userId, String username, String password, String deviceId) {
    // 设置过期时间
    Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
    String token = null;
    try {
      JWTCreator.Builder builder = JWT.create();
      token = builder
              .withClaim("userId", String.valueOf(userId))
              .withClaim("userName", username)
              .withClaim("password", password)
              .withClaim("deviceId", deviceId)
              .withExpiresAt(date)
              .sign(Algorithm.HMAC256(KEY));
    } catch (Exception e) {
      return null;
    }
    return token;
  }

  /**
   * 检验token是否正确
   *
   * @param token 需要校验的token
   * @return 校验是否成功
   */
  public static boolean verify(String token) {
    //设置签名的加密算法：HMAC256
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY)).build();
    try {
      verifier.verify(token);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * 解析token
   *
   * @param token
   * @return
   */
  public static Map<String, String> analysis(String token) {
    Map<String, String> map = new HashMap<>(8);
    if (StrUtil.isBlank(token)) {
      return map;
    }
    DecodedJWT decodedJWT = JWT.decode(token);
    Map<String, Claim> claimsMap = decodedJWT.getClaims();
    for (String key : claimsMap.keySet()) {
      map.put(key, claimsMap.get(key).asString());
    }
    return map;
  }

  /**
   * 验证并解析token
   *
   * @param token
   * @return
   */
  public static Map<String, String> verifyAndAnalysis(String token) {
    Map<String, String> map = new HashMap<>(8);
    if (StrUtil.isBlank(token)) {
      return map;
    }
    try {
      //设置签名的加密算法：HMAC256
      JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY)).build();
      DecodedJWT jwt = verifier.verify(token);
      //验证成功则解析
      Map<String, Claim> claimsMap = jwt.getClaims();
      for (String key : claimsMap.keySet()) {
        map.put(key, claimsMap.get(key).asString());
      }
      return map;
    } catch (Exception e) {
      return map;
    }
  }

  /**
   * 验证并解析token来获取用户名
   *
   * @param token
   * @return
   */
  public static String verifyGetUserName(String token) {
    Map<String, String> map = new HashMap<>(8);
    if (StrUtil.isBlank(token)) {
      return null;
    }
    try {
      //设置签名的加密算法：HMAC256
      JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY)).build();
      DecodedJWT jwt = verifier.verify(token);
      //验证成功则解析
      Map<String, Claim> claimsMap = jwt.getClaims();
      return claimsMap.get("userName").asString();
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 验证并解析token来获取用户名
   *
   * @param token
   * @return
   */
  public static String verifyGetUserId(String token) {
    Map<String, String> map = new HashMap<>(8);
    if (StrUtil.isBlank(token)) {
      return null;
    }
    try {
      //设置签名的加密算法：HMAC256
      JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY)).build();
      DecodedJWT jwt = verifier.verify(token);
      //验证成功则解析
      Map<String, Claim> claimsMap = jwt.getClaims();
      String id =  claimsMap.get("userId").asString();
      return claimsMap.get("userId").asString();
    } catch (Exception e) {
      return null;
    }
  }

}