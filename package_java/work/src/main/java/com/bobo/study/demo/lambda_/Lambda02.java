package com.bobo.study.demo.lambda_;

import java.util.function.IntBinaryOperator;

public class Lambda02 {
  public static void main(String[] args) {
    // 例二

    calculateNum(new IntBinaryOperator() {

      @Override
      public int applyAsInt(int left, int right) {
        return left + right;
      }

    });

    calculateNum((int left, int right)->left + right);

  }

  public static int calculateNum(IntBinaryOperator operator) {
    int a = 10;
    int b = 20;
    return operator.applyAsInt(a, b);
  }

}
