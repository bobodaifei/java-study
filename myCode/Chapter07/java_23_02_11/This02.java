package Chapter07.java_23_02_11;

public class This02 {
  public static void main(String[] args) {
    T_This02 t_This02 = new T_This02();
    t_This02.f2();
  }
}

class T_This02{
  public T_This02(){
    this("bobo");
    System.out.println("构造器");
  }
  

  public T_This02(String name) {
    System.out.println(name);
  }

  public void f1() {
    System.out.println("f1");
  }
  public void f2() {
    System.out.println("f2");
    f1();
    this.f1();
  }
}
