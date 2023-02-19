package com.bobo.study.chapter13.StringBuffer_;

import java.util.Arrays;
import java.util.Comparator;

public class StringBuffer01 {
  public static <T> void main(String[] args) {
    // 在AbstractStringBuilder中有个char[] value;
    StringBuffer stringBuffer = new StringBuffer();
    Integer arr[] = { 1, 5, 6, 4, 3, 4 };
    Arrays.sort(arr);
    Arrays.sort(arr, new Comparator() {

      @Override
      public int compare(Object o1, Object o2) {
        Integer i1 = (Integer) o1;
        Integer i2 = (Integer) o2;
        return i1-i2;
      }

    });
    System.out.println(Arrays.toString(arr));
  }
}
