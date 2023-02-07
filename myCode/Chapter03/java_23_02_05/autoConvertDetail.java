package java_23_02_05;

import java.lang.ProcessBuilder.Redirect.Type;

public class AutoConvertDetail {
  public static void main(String[] args) {
    int n1 = 10;
    double d1 = n1 + 1.1;
    float f1 = n1 + 1.1F;

    byte b1 = 10;//先判断10是否在byte范围内
    short s1 = 10;
    System.out.println(getType(b1 + s1));
    // byte b2 = n2; 错误的

  }
  
  private static String getType(Object a) {
    return a.getClass().toString();
  }
}
