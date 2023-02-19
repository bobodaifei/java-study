package com.bobo.study.chapter09.object_;

public class HashCode01 {
  public static void main(String[] args) {
    AA aa1 = new AA();
    AA aa2 = new AA();
    AA aa3 = aa1;
    System.out.println(aa1.hashCode());
    System.out.println(aa2.hashCode());
    System.out.println(aa3.hashCode());
    System.out.println(aa1.toString());
  }
}

class AA {

}
