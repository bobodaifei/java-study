
public class EnumMethod {
  public static void main(String[] args) {
    Seasion2 spring = Seasion2.SPRING;
    Seasion2 summer = Seasion2.SUMMER;
    System.out.println(spring.name());
    System.out.println(summer.ordinal());
    // System.out.println(Seasion2.values());
    for (Seasion2 seasion2 : Seasion2.values()) {
      
      System.out.println(seasion2.getName());
    }

    Seasion2 spring1 = Seasion2.valueOf("SPRING");
    System.out.println(spring1.name());
    

  }
}

