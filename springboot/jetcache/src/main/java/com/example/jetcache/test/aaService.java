package com.example.jetcache.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class aaService {

  @Autowired
  Smsutol smsutol;

  public void sendSmsCode(String phoneNumber) {
    String code = smsutol.generateCode(phoneNumber);
    // 模拟发送短信验证码的逻辑
    System.out.println("发送短信验证码到 " + phoneNumber + "，验证码为：" + code);
  }

  public boolean checkSmsCode(String phoneNumber, String code1) {
    String code = smsutol.generateCode(phoneNumber);
    if (code.equals(code1)) {
      // 模拟发送短信验证码的逻辑
      System.out.println("success");
      smsutol.deleteCode(phoneNumber);
      return true;
    }
    System.out.println("false");
    return false;

  }

}
