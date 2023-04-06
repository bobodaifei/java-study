package com.bobo.study.demo.regEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx013 {
  public static void main(String[] args) {
    String content = "我....我要....学学学学....编程java!";
    Pattern pattern = Pattern.compile("\\.");
    Matcher matcher = pattern.matcher(content);
    String replaceAll = matcher.replaceAll("");
    System.out.println(replaceAll);
    pattern = Pattern.compile("(.)\\1+");
    matcher = pattern.matcher(replaceAll);
    while (matcher.find()) {
      System.out.println(matcher.group(0));
    }
    String replaceAll2 = matcher.replaceAll("$1");
    System.out.println(replaceAll2);

    //一次性搞定
    String replaceAll3 = Pattern.compile("(.)\1+").matcher(content).replaceAll("replaceAll3");
  }
}
