package Chapter07.java_23_02_10;

public class Recursion02 {
  public static void main(String[] args) {
    T1 t = new T1();
    System.out.println(t.fibonacci(3));// 第三个斐波那契
    int n = 1;
    System.out.println("第" + n + "天" + t.monkeyEatPeaches(n));
  }
}

class T1 {
  public int fibonacci(int n) {
    if (n == 1 || n == 2) {
      return 1;
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }

  public int monkeyEatPeaches(int n) {

    if (n == 10) {
      return 1;
    } else {
      return (monkeyEatPeaches(n + 1) + 1) * 2;
    }

  }
}