package com.bobo.study.demo.regEx;

import java.util.regex.Pattern;

public class RegEx10 {
  public static void main(String[] args) {
    String content = "hello abc hello, 韩顺平教育";
    // String regStr = "hello";
    String regStr = "hello.*";

    boolean matches = Pattern.matches(regStr, content);
    System.out.println("整体匹配= " + matches);

    
  }
}
