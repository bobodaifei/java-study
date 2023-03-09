package com.bobo.study.chapter14.set_;

import java.util.Arrays;

public class HashSetStructure {
  public static void main(String[] args) {
    // 模拟hashmap的底层
    Node[] table = new Node[16];
    Node join = new Node("join", null);
    table[2] = join;
    Node tom = new Node("tom", null);
    join.next=tom;
    
  }
}

class Node {
  public Node next;
  Object object;

  public Node(Object object) {
    this.object = object;
  }

  public Node(Object object, Node next) {
    this.next = next;
    this.object = object;
  }

}