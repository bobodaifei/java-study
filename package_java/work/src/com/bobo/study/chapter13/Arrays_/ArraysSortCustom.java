package com.bobo.study.chapter13.Arrays_;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysSortCustom {
  public static void main(String[] args) {
    int[] arr = { 1, 6, 8, 1, 6, 9, 4, 2, 6, 8 };
    bubble2(arr, new Comparator() {

      @Override
      public int compare(Object o1, Object o2) {
        int i1 = (int)o1;
        int i2 = (int)o2;
        return i2-i1;
        
      }
      
    });

    // bubble(arr);
    System.out.println(Arrays.toString(arr));

  }

  public static void bubble(int[] arr) {
    boolean over = true;
    int tmp ;
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          tmp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = tmp;
          over = false;
        }
      }
      if (over) {
        break;
      }
    }
  }

  public static void bubble2(int[] arr,Comparator c) {
    boolean over = true;
    int tmp;
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (c.compare(arr[j], arr[j+1])>0) {//决定大小顺序
          tmp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = tmp;
          over = false;
        }
      }
      if (over) {
        break;
      }
    }
  }

}
