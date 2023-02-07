package Chapter04.java_23_02_06;

import java.util.Scanner;

public class Input {
  public static void main(String[] args) {

    Scanner inputScanner = new Scanner(System.in);
    String name = inputScanner.next();
    int age = inputScanner.nextInt();
    System.out.println(name);
    System.out.println(age);

  }
}
