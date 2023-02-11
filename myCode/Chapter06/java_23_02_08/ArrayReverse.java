package Chapter06.java_23_02_08;

public class ArrayReverse {
  public static void main(String[] args) {
    int[] arr1 = { 1, 2, 3, 4, 5, 6, 7 };
    int temp ;
    for(int i = 0;i<arr1.length/2;i++){
      temp = arr1[i];
      arr1[i]=arr1[arr1.length-1-i];
      arr1[arr1.length-1-i] = temp;
    }
    for(int j = 0;j<arr1.length;j++){
      System.out.println(arr1[j]);
    }
  }
}
