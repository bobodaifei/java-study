package Chapter05.java_23_02_07;

import java.util.Scanner;

public class BreakExercise {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String name= "";
    String password = "";
    for(int i = 0; i<3;i++){
      System.out.println("请输入账号");
      name=scanner.next();
      System.out.println("请输入账号");
      password = scanner.next();
      if ("张三".equals(name)&& "123456".equals(password)) {
        System.out.println("login ok");
        break;
      }else{
        System.out.println("login error");
        System.out.println("你还有"+(3-i-1)+"次机会");
        if (i==2) {
          System.out.println("exit login");
        }
      }
    }
    
  }
}
