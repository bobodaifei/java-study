package Chapter07.java_23_02_11;

public class HomeWork03 {
  public static void main(String[] args) {
    Book book = new Book("书1",90);
    book.updatePrice();
  }
}

class Book {
  String name;
  int price;
  public Book(String name, int price) {
    this.name = name ;
    this.price = price;
  }

  public void updatePrice() {
    if (this.price > 150) {
      this.price = 150;
      System.out.println(this.price);
    } else if (this.price > 100) {
      this.price = 100;
      System.out.println(this.price);
    } else {
      System.out.println("书本价目不变");
    }
  }
}
