package com.bobo.study.chapter14.collection_;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Collection01 {
  public static void main(String[] args) {
    List list = new ArrayList();
    Integer foo = new Integer(111);
    
    list.add("arrayList");
    list.add(123);
    list.add(foo);
    System.out.println(list);
    
    list.remove((Integer)123);
    System.out.println(list);

    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Object next = iterator.next();
      System.out.println(next);
    }
    for (Object object : list) {
      System.out.println(object);
    }
  }
}
