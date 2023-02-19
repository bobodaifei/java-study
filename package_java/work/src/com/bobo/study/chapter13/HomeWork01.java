package com.bobo.study.chapter13;

public class HomeWork01 {
  public static void main(String[] args) {
    // String str = "abcdef";
    // String str1 = reverse(str, 0, 5);
    // System.out.println(str1);
    String str2 = "Jone Comsd sufudf";
    printName(str2);
  }

  public static String reverse(String str, int start, int end) {
    if (!(str != null && start >= 0 && end <= str.length() - 1 && end > start)) {
      throw new RuntimeException("参数不正确");
    }
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(str.substring(0, start));
    for (int i = end; i >= start; i--) {
      stringBuffer.append(str.charAt(i));
    }
    stringBuffer.append(str.substring(end + 1, str.length()));

    return stringBuffer.toString();
  }

  public static void printName(String str) {
    if (str == null) {
      return;
    }
    String[] str1 = str.split(" ");
    if (str1.length != 3) {
      return;
    }
    
    String foString = String.format("%s,%c,%s",str1[2], str1[1].charAt(0), str1[0]);
    System.out.println(foString);
    // System.out.println(str1[2] + " " + str1[1].charAt(0) + " " + str1[0]);
  }
}
