package Chapter07.java_23_02_10;

public class OverLoad01 {
  public static void main(String[] args) {

    MyCalculator myCalculator = new MyCalculator();
    myCalculator.calculate(1.0, 1);
  }
}

class MyCalculator {
  public String calculate(String m1, int n2) {
    return m1 + n2;
  }
  public int calculate(int m1) {
    return m1;
  }
  public void calculate() {
    System.out.println(1);
  }

  public double calculate(double n1, int n2) {
    return n1 + n2;
  }

  public double calculate(int n1, double n2) {
    return n1 + n2;
  }

  public int calculate(int n1, int n2, int n3) {
    return n1 + n2;
  }

}