package Chapter07.java_23_02_09;

public class CopyExercise {
  public static void main(String[] args) {
    Persion p1 = new Persion();
    Persion p3 = new Persion();
    p1.name = "张三";
    p1.age = 10;
    MyTools myTools = new MyTools();
    Persion p2 = myTools.copyPersion(p1);
    System.out.println(p1);
    System.out.println(p2);
    System.out.println(p1==p2);
  }

}

class MyTools {
  public Persion copyPersion(Persion p) {
    Persion copyPersion = new Persion();
    copyPersion.name = p.name;
    copyPersion.age = p.age;
    return copyPersion;
  }
}

class Persion {
  public String name;
  protected String name1;
  String name2;
  private String age;
}
