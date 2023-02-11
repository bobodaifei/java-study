package Chapter07.java_23_02_09;

public class PropertiesDetail {
  public static void main(String[] args) {
    Persion persion1 = new Persion();
    persion1.setName("null1");
    System.out.println(persion1.name);
    persion1=null;
    System.out.println(persion1);
  }
}

class Persion{
  String name;
  int age;
  double sal;
  boolean isPass;

  public void setName(String name) {
    this.name = name;
  }
}
