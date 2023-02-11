package Chapter05.java_23_02_07;

public class Exercise03 {
  public static void main(String[] args) {
    int count = 0;
    int sum = 0;
    for (int i = 1; i <= 100; i++) {
      if (sum == 5) {
        System.out.println("");
        sum = 0;
      }
      if (i % 5 == 0) {
        sum++;
        count++;
        System.out.print(i+" ");
      }
    }
    System.out.println(count);
  }
}
