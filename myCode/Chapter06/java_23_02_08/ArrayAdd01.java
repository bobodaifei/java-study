package Chapter06.java_23_02_08;

public class ArrayAdd01 {
  public static void main(String[] args) {
    double[] array = { 1, 2, 3, 4, 5 };
    double[] array1 = new double[array.length + 1];
    for(int i = 0;i<array.length;i++){
      array1[i]=array[i];
    }
    for (int i = 0; i < array1.length; i++) {
      System.out.println(array1[i]);
    }
    array=array1;
  }
}
