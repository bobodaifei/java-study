package com.bobo.study.demo.regEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx03 {
  public static void main(String[] args) {
    String content = "abc$(a.bc(123( )";
    // 匹配( => \\(
    // 匹配. => \\. 直接写.代表所有字符
    // String regStr = "\\.";
    // String regStr = "\\d\\d\\d"; 代表三个数字
    String regStr = "\\d{3}";// 代表\\d出现三次
    String regStr1 = "\\d{3}(\\d)?";// ?代表可能存在
    String regStr2 = "a..b";// 以a开头b结尾，中间包括任意两个字符的长度为4的字符串
    // java正则表达式默认区分大小写，如何不区分大小写
    String regStr4 = "(?i)abc"; // abc不区分大小写
    String regStr5 = "a(?i)bc"; // bc不区分大小写
    String regStr6 = "ab(?i)c"; // c不区分大小写
    // Pattern pattern = Pattern.compile(regStr3,Pattern.CASE_INSENSITIVE); //直接不区分大小写
    // String regStr = "[a-z]";//匹配 a-z之间任意一个字符
    // String regStr = "[A-Z]";//匹配 A-Z之间任意一个字符
    // String regStr = "abc";//匹配 abc 字符串[默认区分大小写]
    // String regStr = "[0-9]";//匹配 0-9 之间任意一个字符
    // String regStr = "[^a-z]";//匹配 不在 a-z之间任意一个字符
    // String regStr = "[^0-9]";//匹配 不在 0-9之间任意一个字符
    // String regStr = "[abcd]";//匹配 在 abcd中任意一个字符
    // String regStr = "\\D";//匹配 不在 0-9的任意一个字符
    // String regStr = "\\w";//匹配 大小写英文字母, 数字，下划线
    // String regStr = "\\W";//匹配 等价于 [^a-zA-Z0-9_]
    // // \\s 匹配任何空白字符(空格,制表符等)
    // String regStr = "\\s";
    // // \\S 匹配任何非空白字符 ,和\\s刚好相反
    // String regStr = "\\S";
    // // . 匹配出 \n 之外的所有字符,如果要匹配.本身则需要使用 \\.

    Pattern pattern = Pattern.compile(regStr);
    Matcher matcher = pattern.matcher(content);

    while (matcher.find()) {
      System.out.println("找到 " + matcher.group(0));
    }

  }
}
