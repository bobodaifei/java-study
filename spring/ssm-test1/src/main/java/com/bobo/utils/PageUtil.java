package com.bobo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
public class PageUtil {

  @Data
  @AllArgsConstructor
  public class Page {
    private int start;
    private int end;
    private int pageSize;
    private int pageNum;
  }

  public Page pageAdapt(int pageNum, int pageSize, long total) {
    int start = (pageNum - 1) * pageSize;
    if (start >= total) {
      pageNum--;
      start = start - pageSize;
    }
    if (pageNum < 1) {
      pageNum = 1;
      start = 0;
    }
    int end = (((total - start) >= pageSize) ? pageSize : (int) (total - start)) + start;
    return new Page(start, end, pageNum, pageSize);
  }
}
