package Chapter05.java_23_02_07;

public class Exercise01 {
  public static void main(String[] args) {
    int money = 100000;
    int count = 0;
    while (money >= 1000) {
      if (money > 50000) {
        money -= money * 0.05;
      } else {
        money -= 1000;
      }
      count++;
    }
    System.out.println("通过了" + count);
  }
}
