package Chapter06.java_23_02_08;

public class BubbleSort {
  public static void main(String[] args) {
    int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8 };
    for (int i = arr1.length - 1; i > 0; i--) {
      boolean overSort = true;
      for (int j = 0; j < i; j++) {
        if (arr1[j] > arr1[j + 1]) {
          int temp = arr1[j];
          arr1[j] = arr1[j + 1];
          arr1[j + 1] = temp;
          overSort = false;
        }
      }
      if (overSort) {
        break;
      }
    }
    for (int j = 0; j < arr1.length; j++) {
      System.out.println(arr1[j]);
    }
  }
}
