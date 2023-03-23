package com.bobo.study.demo.stream;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode//用于后期去重 重写了EqualsAndHashCode
public class Author {
  private Long id;
  private String name;
  private Integer age;
  private String intro;
  private List<Book> books;
  
}
