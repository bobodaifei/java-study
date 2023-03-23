package com.bobo.study.chapter14.set_;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSet01 {
  public static void main(String[] args) {
    TreeSet treeSet = new TreeSet(new Comparator() {

      @Override
      public int compare(Object o1, Object o2) {
        return ((String) o1).compareTo((String) o2);
      }

    });
    treeSet.add("1111");
    treeSet.add("2222");
    treeSet.add("3333");
    treeSet.add("4444");
    treeSet.add("5555");

    System.out.println(treeSet);

  }
}
