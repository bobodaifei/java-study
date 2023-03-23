package com.bobo.study.demo.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode // 用于后期去重 重写了EqualsAndHashCode
public class Book {
  private Long id;
  private String name;
  private String category;//"哲学，小说"
  private Integer score;
}
