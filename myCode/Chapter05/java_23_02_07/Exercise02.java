package Chapter05.java_23_02_07;

import java.util.Scanner;

public class Exercise02 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int num = scanner.nextInt();
    int temp = num;
    int sum = 0;
    while (temp > 0) {
      sum = (int) (Math.pow(temp % 10, 3) + sum);
      temp = temp / 10;
    }
    if (sum == num) {
      System.out.println("1");
    } else {
      System.out.println("2");
    }
  }
}
