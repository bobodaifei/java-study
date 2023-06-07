package com.example.ioc.test;

public class StringUtils {

  public static String getSetterName(String name) {
    return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
  }
}
