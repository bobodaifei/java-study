package com.bobo.study.work.work03;

public class MySelf {
  
  public void myDrink(Drink drink) {
    drink.myTaste();
  }

  public Drink myDrinkType(String dirinkType) {
    if ("Water".equals(dirinkType)) {
      return new Water();
    }
    if ("Coffee".equals(dirinkType)) {
      return new Coffee();
    }
    if ("Juice".equals(dirinkType)) {
      return new Juice();
    }
    if ("Milk".equals(dirinkType)) {
      return new Milk();
    }
    return null;
  }

  public static void main(String[] args) {

    MySelf mySelf = new MySelf();
    System.out.println(mySelf.myDrinkType("Coffee"));

    mySelf.myDrink(new Water());

    Coffee coffee = new Coffee();
    mySelf.myDrink(coffee);

    Juice juice = new Juice();
    mySelf.myDrink(juice);

    Milk milk = new Milk();
    mySelf.myDrink(milk);
  }

}

class Drink{
  public void myTaste() {
    System.out.println("我是饮料");
  }
  
}
class Water extends Drink{
  public void myTaste() {
    System.out.println("水味道");
  }
}
class Coffee extends Drink{
  public void myTaste() {
    System.out.println("咖啡味道");
  }
}
class Juice extends Drink{
  public void myTaste() {
    System.out.println("Juice味道");
  }
}
class Milk extends Drink{
  public void myTaste() {
    System.out.println("牛奶味道");
  }
}
