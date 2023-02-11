package Chapter07.java_23_02_11;

public class HomeWork01 {
  public static void main(String[] args) {
    double[] d1 = { 1 };
    A01 a01 = new A01();
    if (a01.max(d1) != null) {
      System.out.println(a01.max(d1));
    } else {
      System.out.println("kong");
    }
  }
}

class A01 {
  public Double max(double[] d1) {
    if (d1 != null && d1.length > 0) {
      double dMax = d1[0];
      for (int i = 1; i < d1.length; i++) {
        System.out.println(d1[i]);
        if (dMax < d1[i]) {
          dMax = d1[i];
        }
      }
      return dMax;
    } else {
      return null;
    }

  }
}