package com.bobo.study.demo.lambda_;

import java.util.function.IntConsumer;

public class Lambda06 {
  public static void main(String[] args) {
    foreachArr(new IntConsumer() {

      @Override
      public void accept(int value) {
        if (value % 2 == 0) {
          System.out.println(value);
        }
      }

    });

    foreachArr(value -> {
      if (value % 2 == 0) {
        System.out.println(value);
      }
    });
  }

  public static void foreachArr(IntConsumer consumer) {
    int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    for (int i : arr) {
      consumer.accept(i);
    }
  }

}
