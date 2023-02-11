package Chapter05.java_23_02_07;

import java.util.Scanner;

public class NestedIf {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    double score = scanner.nextDouble();
    if (score > 8.0) {
      System.out.println("晋级成功");
      char gender = scanner.next().charAt(0);
      if (gender == '男') {
        System.out.println("man");
      }else{
        System.out.println("woman");
      }
    }else{
      System.out.println("晋级失败");
    }
    
    
  }
}
