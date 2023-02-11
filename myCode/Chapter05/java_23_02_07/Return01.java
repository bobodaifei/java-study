package Chapter05.java_23_02_07;

public class Return01 {
  public static void main(String[] args) {
    label1: for (int i = 1; i <= 10; i++) {
      System.out.println("i=" + i);
      label2: for (int j = 1; j <= i; j++) {
        if (i == 3) {
          return;
        }
        System.out.println(j);
      }
    }
  }
}
