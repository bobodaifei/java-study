package Chapter06.java_23_02_08;


public class HomeWork02 {
  public static void main(String[] args) {
    int[] arr = new int[10];
    
    for(int i = 0;i<10;i++){
      arr[i]= (int) (Math.random() * 100 + 1);
    }
    for (int i = 0; i < 10; i++) {
      System.out.println(arr[i]);
    }
  }
}
