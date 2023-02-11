package Chapter07.java_23_02_11;

public class Constructor01 {
  public static void main(String[] args) {

    ConstructorTest1 constructorTest1 = new ConstructorTest1("bobo",8,32);
    Dog dog = new Dog("11");
    
  }
}
class Dog{

  /*
  默认构造器
  Dog(){

  }
   */
  Dog(String name){

  }
}

class ConstructorTest1 {
  String name;
  int age;

  public ConstructorTest1(String Pname, int pAge) {
    name = Pname;
    age = pAge;
  }

  public ConstructorTest1(String Pname, int pAge,int score) {
    name = Pname;
    age = pAge;
  }
  
  public ConstructorTest1(String Pname, int... pAge) {
    name = Pname;
  }
}
