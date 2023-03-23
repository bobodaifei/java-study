package com.bobo.study.chapter09.debug_;

import java.util.Arrays;

public class Debug02 {
  public static void main(String[] args) {
    int[] arr = { 0, 2, 5, 6, 3, 1 };
    Arrays.sort(arr);
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }
}
