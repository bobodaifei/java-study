
public class EnumEx {
  public static void main(String[] args) {
    for (Week week : Week.values()) {
      System.out.println(week.name());
      System.out.println(week.getName ());
    }
  }
}

enum Week{
  MONDAY("星期1"),
  TUESDAY("星期2"),
  WEDNESDAY("星期3"),
  THURSDAY("星期4"),
  FRIDAY("星期5"),
  SATURDAY("星期6"),
  SUNDAY("星期7");
  private String name;

  private Week(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  

}
