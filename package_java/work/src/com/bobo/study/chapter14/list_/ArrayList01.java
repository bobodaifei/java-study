package com.bobo.study.chapter14.list_;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayList01 {
  public static void main(String[] args) {
    ArrayList list = new ArrayList();
    Integer foo = new Integer(111);

    list.add("arrayList1");
    list.add("arrayList2");
    list.add("arrayList3");
    list.add("arrayList4");
    list.add("arrayList5");
    list.add("arrayList6");
    list.add("arrayList7");
    list.add("arrayList8");
    list.add("arrayList9");
    list.add("arrayList0");
    list.add(1, "bobo");
    
    System.out.println(list.get(4));
    list.remove(5);
    list.set(6, "foo");
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      System.out.println(object);
    }
    System.out.println(list);

  }
}
