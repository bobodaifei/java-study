package com.bobo.study.chapter14.set_;

import java.util.HashSet;
import java.util.Set;

public class Set01 {
  public static void main(String[] args) {
    Set set = new HashSet();
    set.add("set1");
    set.add("set2");
    set.add("set3");
    set.add("set4");
    set.add("se5t");
    for (Object object : set) {
      System.out.println(object);
    }
  }
}
