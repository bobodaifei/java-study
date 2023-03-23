package com.bobo.study.chapter14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collections_ {
  public static void main(String[] args) {
    List list = new ArrayList();
    list.add("list1");
    list.add("list2");
    list.add("list3");
    list.add("list4");
    list.add("list5");
    Collections.reverse(list);
    System.out.println(list);
    Collections.copy(null, null);
  }
}
