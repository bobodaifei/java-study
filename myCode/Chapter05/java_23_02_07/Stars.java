package Chapter05.java_23_02_07;

import java.util.Scanner;

public class Stars {
  public static void main(String[] args) {

    // Scanner scanner = new Scanner(System.in);
    // int totalLevel = scanner.nextInt();
    // for (int i = 1; i <= totalLevel; i++) {
    //   int j = 1;
    //   for (; j <= totalLevel - i; j++) {
    //     System.out.print(" ");
    //   }
    //   for (;j <= ((2*totalLevel-1)-(totalLevel-i)); j++) {
    //     System.out.print("*");
    //   }
    //   System.out.println("");
    // }

    Scanner scanner = new Scanner(System.in);
    int totalLevel = scanner.nextInt();
    for (int i = 1; i <= totalLevel; i++) {
      int j = 1;
      for (; j <= totalLevel - i; j++) {
        System.out.print(" ");
      }
      for (; j <= (totalLevel + i-1); j++) {
        if (j==(totalLevel - i+1)|| j == (totalLevel + i - 1)||i==totalLevel) {
          System.out.print("*");
        }else {
          System.out.print(" ");
        }
      }
      System.out.println("");
    }
  }
}
