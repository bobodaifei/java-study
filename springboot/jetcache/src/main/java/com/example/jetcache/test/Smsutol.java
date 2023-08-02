package com.example.jetcache.test;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.Cached;
import org.springframework.stereotype.Component;

@Component
public class Smsutol {

  @ (area = "sms", name = "sms_", key = "#phoneNumber", expire = 60)
  public String generateCode(String phoneNumber) {
    // 生成随机的验证码
    return Math.random() + phoneNumber;
  }

  @CacheInvalidate(area = "sms", name = "sms_", key = "#phoneNumber")
  public void deleteCode(String phoneNumber) {

  }

}
