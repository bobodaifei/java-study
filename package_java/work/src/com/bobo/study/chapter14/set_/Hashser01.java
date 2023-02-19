package com.bobo.study.chapter14.set_;

import java.util.HashSet;

public class Hashser01 {
  public static void main(String[] args) {
    HashSet hashSet = new HashSet();
    hashSet.add("hashSet");
    hashSet.add(null);
    hashSet.add(null);
    System.out.println(hashSet.add(null));
    System.out.println(hashSet.add("has3hSet"));
    System.out.println(hashSet.add("hash2S1et"));
    System.out.println(hashSet.add("ha2shSet"));
    System.out.println(hashSet);


    
  }
}
