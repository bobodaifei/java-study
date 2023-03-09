package com.bobo.study.demo.work;

public class MySelf {
  public static void main(String[] args) {
    MySelf mySelf = new MySelf();
    Water water = new Water();
    mySelf.myDrink(water);
    Coffee coffee = new Coffee();
    mySelf.myDrink(coffee);
  }
  
  public void myDrink(Drink drink) {
    drink.name();
  }
}

class Drink{
  public void name() {
    System.out.println("饮料");
  }
  
}
class Water extends Drink{
  public void name() {
    System.out.println("水");
  }
}
class Coffee extends Drink{
  public void name() {
    System.out.println("咖啡");
  }
}
class Juice extends Drink{
  public void name() {
    System.out.println("Juice");
  }
}
class Milk extends Drink{
  public void name() {
    System.out.println("牛奶");
  }
}
