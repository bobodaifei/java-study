package Chapter07.java_23_02_11;

public class Scope01 {
  public static void main(String[] args) {
    Test test = new Test();
    test.beforeSay();
    test.afterSay();
  }
}

class Test{
  int num;
  public void beforeSay() {
    int num = 10;
    System.out.println(num);
  }
  
  public void afterSay() {
    
    System.out.println(num);
    int num = 10;
  }
}
