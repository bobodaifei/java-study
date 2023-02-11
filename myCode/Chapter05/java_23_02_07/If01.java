package Chapter05.java_23_02_07;

import java.util.Scanner;

public class If01 {
  public static void main(String[] args) {
    boolean a = false;
    if (a = true) {
      System.out.println("fuck you man");
    }
    Scanner scanner = new Scanner(System.in);
    int age = scanner.nextInt();
    if (age<25&&age>10) {
      System.out.println("年轻人");
    }else{
      System.out.println("老了");
    }
  }
}
