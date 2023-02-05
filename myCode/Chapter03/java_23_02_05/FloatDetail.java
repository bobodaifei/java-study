package java_23_02_05;

public class FloatDetail {
  
  public static void main(String[] args) {
    float n1 = 1.1F;
    double n2 = 1.1;
    double n3 = 1.1f;

    double n4 = 5.12;
    double n5 = 5.12f;
    double n6 = .12;

    double n7 = 5.12e2;
    double n8 = 5.12E-2;
    System.out.print(n7+"\n");
    System.out.print(n8+"\n");

    double num9 = 2.1234567851;
    float num10 = 2.1234567851F;
    System.out.print(num9+"\n");
    System.out.print(num10+"\n");

    double n11 = 8.1/3;//易错（计算器问题）
    double n12 = 2.7;
    System.out.print(n11 + "\n");
    System.out.print(n12 + "\n");
    System.out.print(Math.abs(n11 - n12));
    if (Math.abs(n11 - n12)<0.0000001) {
      System.out.print("差值达到规定范围，可忽略认为相等");
    }
  }
}
