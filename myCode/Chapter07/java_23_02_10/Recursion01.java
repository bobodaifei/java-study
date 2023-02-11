package Chapter07.java_23_02_10;

public class Recursion01 {
  public static void main(String[] args) {
    T t = new T();
    t.test(5);
  }
}

class T {
  public void test(int n) {
    if (n > 2) {
      test(n - 1);
    }
    System.out.println(n);
  }

  public int factorial(int n) {
    if (n == 1) {
      return 1;
    } else {
      return factorial(n - 1) * n;
    }

  }
}