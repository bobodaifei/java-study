package Chapter06.java_23_02_08;

public class ArrayAdd02 {
  public static void main(String[] args) {
    double[] array = { 1, 2, 3, 4, 5 };
    double[] array1 = new double[array.length * 2];
    System.arraycopy(array, 0, array1, 0, array.length);
    array = array1;
    System.out.println(array.length);
  }
}
