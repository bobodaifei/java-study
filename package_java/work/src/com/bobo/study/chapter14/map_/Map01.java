package com.bobo.study.chapter14.map_;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Map01 {
  public static void main(String[] args) {
    HashMap map = new HashMap();
    map.put("no1", "奥里给");
    map.put("no12", "奥里给");

    Set set =  map.entrySet();
    for (Object object : set) {
      Map.Entry entry = (Map.Entry) object;
      System.out.println(entry.getKey());
    }
    Set set1 = map.keySet();
    for (Object object : set1) {

      System.out.println(object.toString());
    }
    
  }
  
  // public static void main(String[] args) {
  //   AA1 aa = new BB1();
  //   aa.name();
  // }
}
interface AA1{
  public void name();
}
class BB1 implements AA1{

  @Override
  public void name() {
    // TODO Auto-generated method stub
    System.out.println("BB");
  }

  

}