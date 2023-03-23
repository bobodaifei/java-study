package com.bobo.study.chapter14.list_;

public class LinkedList01 {
  public static void main(String[] args) {
    Node node1 = new Node("jack1");
    Node node2 = new Node("jack2");
    Node node3 = new Node("jack3");
    node1.next = node2;
    node2.next = node3;
    node3.pref = node2;
    node2.pref = node1;

    Node first = node1;
    Node last = node2;
    Node temp = first;
    while(temp!=null){
      System.out.println(temp.toString());
      temp=temp.next;
    }
    Node node4 = new Node("jack4");
    node4.next = node2;
    node4.pref = node1;
    node1.next = node4;
    node2.pref = node4;
    temp = first;
    while (temp != null) {
      System.out.println(temp.toString());
      temp = temp.next;
    }
  }
}

class Node {
  public Node pref;
  public Node next;
  private Object item;

  public Node(Object item) {
    this.item = item;
  }

  @Override
  public String toString() {
    return "Node [item=" + item + "]";
  }

  
}
