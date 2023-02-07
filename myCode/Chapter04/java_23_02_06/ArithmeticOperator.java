package Chapter04.java_23_02_06;

public class ArithmeticOperator {
  public static void main(String[] args) {

    double d1 = 10 / 4;
    System.out.println(d1);// 2.0

    // java中取模公式 a % b = a - (int)(a / b * b)
    System.out.println(-10 % 3);// -1
    System.out.println(-10 % -3);// -1
    System.out.println(10 % -3);// 1
    System.out.println(10 % 3);// 1

    System.out.println(10 > 30);
    System.out.println(false ^ true);
  }
}
