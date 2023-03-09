package com.bobo.study.chapter14;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class HomeWork02 {
  public static void main(String[] args) {
    Map map = new HashMap();
    map.put("jack", 650);
    map.put("tom", 1200);
    map.put("smith", 2900);
    System.out.println(map);
    
    map.put("jack", 2600);
    System.out.println(map);

    Set set = map.entrySet();
    for (Object object : set) {
      Map.Entry entry = (Map.Entry)object;
      entry.setValue(100);
    }
    System.out.println(map);
    Collection collection =  map.values();
    Iterator iterator1 = collection.iterator();
    while (iterator1.hasNext()) {
      int object = (int)iterator1.next();
      System.out.println(object);
    }


    Set set1 = map.keySet();
    Iterator iterator = set1.iterator();
    while (iterator.hasNext()) {
      String str = (String)iterator.next();
      System.out.println(str);
    }
  }
}

class emp{
  private String name;
  private double sal;
  public emp(String name, double sal) {
    this.name = name;
    this.sal = sal;
  }
  
}
