package com.bobo.study.chapter14.list_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

@SuppressWarnings({ "all" })
public class List01 {
  public static <T> void main(String[] args) {
    List list = new Vector();
    list.add(new Book("红楼梦", 100, "曹雪芹"));
    list.add(new Book("西游记", 10, "吴承恩"));
    list.add(new Book("水浒传", 9, "施耐庵"));
    list.add(new Book("三国演义", 80, "罗贯中"));
    list.add(new Book("西游记", 10, "吴承恩"));
    // list.sort(new Comparator() {

    // @Override
    // public int compare(Object o1, Object o2) {

    // return (int)(((Book) o1).getPrice() - ((Book) o2).getPrice());
    // }

    // });
    bubble1(list, new Comparator<T>() {

      @Override
      public int compare(T o1, T o2) {
        // TODO Auto-generated method stub
        return (int) (((Book) o1).getPrice() - ((Book) o2).getPrice());
      }

    });

    for (Object object : list) {
      System.out.println(object);
    }

  }

  public static void bubble1(List list, Comparator c) {
    boolean over = true;
    int tmp;
    for (int i = 0; i < list.size() - 1; i++) {
      for (int j = 0; j < list.size() - i - 1; j++) {
        Book b1 = (Book) list.get(j);
        Book b2 = (Book) list.get(j + 1);
        if (c.compare(b1, b2) > 0) {
          list.set(j, b2);
          list.set(j + 1, b1);
          over = false;
        }
      }
      if (over) {
        break;
      }
    }
  }

  public static void bubble2(int[] arr, Comparator c) {
    boolean over = true;
    int tmp;
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (c.compare(arr[j], arr[j + 1]) > 0) {// 决定大小顺序
          tmp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = tmp;
          over = false;
        }
      }
      if (over) {
        break;
      }
    }
  }
}

class Book {
  private String name;
  private double price;
  private String auther;

  public Book(String name, double price, String auther) {
    this.name = name;
    this.price = price;
    this.auther = auther;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getAuther() {
    return auther;
  }

  public void setAuther(String auther) {
    this.auther = auther;
  }

  @Override
  public String toString() {
    return "Book [name=" + name + ", price=" + price + ", auther=" + auther + "]";
  }

}