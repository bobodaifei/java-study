package com.example.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Body {
  private String code;


  public static void main(String[] args) {
    List<Object> list = new ArrayList<>();

    Iterator<Object> iterator = list.iterator();

    while (iterator.hasNext()) {
      Object next =  iterator.next();
      System.out.println(next);
    }

    list.forEach(item->{
      System.out.println(item);
    });
  }
}
