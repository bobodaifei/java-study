package Chapter05.java_23_02_07;

public class While01 {
  public static void main(String[] args) {
    int i = 0;
    while (i <= 100) {
      if (i % 3 == 0) {
        System.out.println(i);
      }
      i++;
    }
    int j = 40;
    while (j <= 200) {
      if (j % 2 == 0) {
        System.out.println(j);
      }
      j++;
    }
  }
}
