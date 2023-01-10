//java快速入门

//1.public class Hello 表示Hello是一个类，public公有的类
public class Hello{

  //2.主方法，程序入口
  public static void main(String[] args){
    //3.输出到屏幕上
    System.out.println("hello,徐纪波 is study! ");

  }

}

//编译后，每个class类都会生成一个.class文件
class Dog{
  public static void main(String[] args){
    System.out.println("Dog is study!");
  }
}
class Cat {
  public static void main(String[] args) {
    System.out.println("Cat is study!");
  }
}

//转义符
class ChangeChar {
  public static void main(String[] args) {
    System.out.println("这是一个\t字符,实现\t对齐");
    System.out.println("这是一个\n字符");
    System.out.println("这是一个\\字符");
    System.out.println("这是一个\'字符");
    System.out.println("这是一个\"字符");
    System.out.println("这是一个\r字符");
    System.out.println("书名\t作者\t价格\t销量\n三国\t罗贯中\t120\t1000");
  }
}