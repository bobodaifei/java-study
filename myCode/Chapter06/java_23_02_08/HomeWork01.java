package Chapter06.java_23_02_08;

public class HomeWork01 {
  public static void main(String[] args) {
    int[] arr = { 10, 12, 45, 90 };
    int addNum = 23;
    int index = -1;
    for (int i = 0; i < arr.length; i++) {
      if (addNum <= arr[i]) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      index = arr.length;
    }
    int[] newArr = new int[arr.length + 1];
    for (int i = 0, j = 0; i < newArr.length; i++) {
      if (i != index) {
        newArr[i] = arr[j];
        j++;
      } else {
        newArr[i] = addNum;
      }
    }
    arr=newArr;
    for(int i = 0;i<arr.length;i++){
      System.out.println(arr[i]);
    }
  }
}
