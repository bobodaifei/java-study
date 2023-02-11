package Chapter06.java_23_02_08;

import java.util.Arrays;

public class ArrayAdd03 {
  public static void main(String[] args) {

    double[] array = { 1, 2, 3, 4, 5 };
    double[] array1;
    //副本方式
    array1 = Arrays.copyOf(array, array.length * 2);

    System.out.println(array1.length);
  }
}
