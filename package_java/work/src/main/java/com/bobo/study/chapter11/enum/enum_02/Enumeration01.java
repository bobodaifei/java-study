

public class Enumeration01 {
  public static void main(String[] args) {
    System.out.println(Seasion.a);
    System.out.println(Seasion.SPRING.getName());
    System.out.println(Seasion2.SPRING);
  }
}

class Seasion{
  private String name;
  private String desc;

  //自定义类实现枚举
  public static final Seasion SPRING = new Seasion("春天", "春天在哪里");
  public static final Seasion SUMMER = new Seasion("夏天", "夏天在哪里");
  public static final int a = 0;
  static{
    System.out.println("static");
  }

  private Seasion(String name, String desc) {
    this.name = name;
    this.desc = desc;
  }
  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }
  
}
enum Seasion2{
  // enum实现枚举
  SPRING("春天", "春天在哪里"),
  SUMMER("夏天","夏天在哪里");

  private String name = "aoligei";
  private String desc = "asdasfds";

  
  // public static final Seasion2 SPRING = new Seasion2("春天", "春天在哪里");
  // public static final Seasion2 SUMMER = new Seasion2("夏天", "夏天在哪里");
  // public static final int a = 0;
  // static{
  //   System.out.println("static");
  // }

  private Seasion2(String name, String desc) {
    this.name = name;
    this.desc = desc;
  }
  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }
  
}
