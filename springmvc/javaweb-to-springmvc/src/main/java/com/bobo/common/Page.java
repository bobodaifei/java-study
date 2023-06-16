package com.bobo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString

public class Page<T> {

  protected List<T> records;
  
  protected long total;
  protected long pageSize = 10l;
  protected long currentPage = 1l;

}
