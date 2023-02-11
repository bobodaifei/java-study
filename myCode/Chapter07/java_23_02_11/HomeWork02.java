package Chapter07.java_23_02_11;

public class HomeWork02 {
  public static void main(String[] args) {
    String[] s1 = { "1.0", "3.6", "651", "69" };
    A02 a02 = new A02();
    System.out.println(a02.find(s1, "69"));
  }
}

class A02 {
  public int find(String[] s1, String str1) {
    if (s1 != null && s1.length > 0 && str1 != null) {
      for (int i = 1; i < s1.length; i++) {
        if (s1[i].equals(str1)) {
          return i;
        }
      }
    }
    return -1;
  }
}
