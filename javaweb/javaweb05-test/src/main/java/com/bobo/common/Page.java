package com.bobo.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString

public class Page<T> {

  protected List<T> records;
  
  protected long total;
  protected long pageSize = 10l;
  protected long currentPage = 1l;

}
