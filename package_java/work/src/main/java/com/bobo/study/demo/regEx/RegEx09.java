package com.bobo.study.demo.regEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx09 {
  public static void main(String[] args) {
    //全是汉字
    String content = "波波带飞";
    String regStr = "^[\u0391-\uffe5]+$";

    //邮政编码 1-9开头
    String content1 = "123879";
    String regStr1 = "^[1-9]\\d{5}$";

    //qq号 1-9 开头 5-10位数
    String regStr2 = "^[1-9]\\d{4,9}$";

    //手机号
    String regStr3 = "^1[3458]\\d{9}$";
    // String regStr3 = "^[1(?:3|4|5|8)]\\d{9}$";

    //url
    String content4 = "https://www.bilibili.com/video/BV1Eq4y1E79W?p=17&spm_id_from=pageDriver&vd_source=e343ec5ba894da27bc56b2a378a29833";
    String regStr4 = "^(https?://)?([\\w-]+\\.)+[\\w-]+(\\/[\\w-?=&/%._#]*)?$";
    Pattern pattern = Pattern.compile(regStr4);
    Matcher matcher = pattern.matcher(content4);
    while (matcher.find()) {
      System.out.println("找到: " + matcher.group(0));
    }
  }
}
