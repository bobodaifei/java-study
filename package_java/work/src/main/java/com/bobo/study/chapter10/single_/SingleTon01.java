package com.bobo.study.chapter10.single_;

public class SingleTon01 {
  public static void main(String[] args) {
    System.out.println(GirlFriend.getInstance());
    System.out.println(BoyFriend.getInstance());
  }
}

// 饿汉式
class GirlFriend {
  private String name;
  private static GirlFriend girlFriend = new GirlFriend("小红");

  private GirlFriend(String name) {
    this.name = name;
  }

  public static GirlFriend getInstance() {
    return girlFriend;
  }

  @Override
  public String toString() {
    return "GirlFriend [name=" + name + "]";
  }
  
}

// 懒汉式
class BoyFriend {
  private String name;
  private static BoyFriend boyFriend;

  private BoyFriend(String name) {
    this.name = name;
  }

  public static BoyFriend getInstance() {
    if (boyFriend==null) {
      boyFriend = new BoyFriend("小黑");
    }
    return boyFriend;
  }

  @Override
  public String toString() {
    return "BoyFriend [name=" + name + "]";
  }

}