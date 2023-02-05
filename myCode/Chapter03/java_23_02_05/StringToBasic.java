package java_23_02_05;


public class StringToBasic {
  public static void main(String[] args) {

    //基本数据类型转字符串
    int n1 = 100;
    float f1 = 1.1F;
    double d1 = 4.5;
    String s1 = n1 + "";
    System.out.println(n1+""+f1+""+d1);

    //String转基本数据类型
    int n2 = Integer.parseInt(s1);
    System.out.println(n2);

    System.out.println(s1.charAt(0));
  }
}
