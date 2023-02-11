package Chapter06.java_23_02_08;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Array01 {
  public static void main(String[] args) {
    double[] array = new double[5];
    Scanner scanner = new Scanner(System.in);

    for(int i =0;i< array.length;i++){
      array[i]=scanner.nextDouble();
    }
    System.out.println(array.length);
    
  }
}
