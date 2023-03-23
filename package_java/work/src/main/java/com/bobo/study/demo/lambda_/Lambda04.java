package com.bobo.study.demo.lambda_;

import java.util.function.Function;

public class Lambda04 {
  public static void main(String[] args) {
    typecohver(new Function<String,Integer>() {

      @Override
      public Integer apply(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
      }
      
    });

    typecohver((String t) -> {
      return t.length();
    });

    typecohver(t -> t.length());

  }

  public static <R> R typecohver(Function<String, R> function) {
    String str = "1235";
    R result = function.apply(str);
    return result;
  }

}
