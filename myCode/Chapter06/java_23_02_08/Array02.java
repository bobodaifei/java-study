package Chapter06.java_23_02_08;

import java.util.Arrays;

public class Array02 {
  public static void main(String[] args) {
    double[] array;
    array = new double[5];

    double[] array1 = new double[5];
    System.out.println(array[0]);
    // System.arraycopy(array, 0, array1, 0, array.length);
    array1=Arrays.copyOf(array, array.length*2);
    System.out.println(array1.length);
  }
}
