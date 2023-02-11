package Chapter07.java_23_02_11;

public class This01 {
  public static void main(String[] args) {
    This01_Dog dog = new This01_Dog("GOUZI", 0);
    dog.info();
  }
}
class This01_Dog{
  String name;
  int age;
  public  This01_Dog(String name,int age) {
    this.name = name ;
    this.age = age;
  }
  
  public This01_Dog() {

  }
  public void info() {
    System.out.println(this.name);
    System.out.println(this.age);
  }
}
