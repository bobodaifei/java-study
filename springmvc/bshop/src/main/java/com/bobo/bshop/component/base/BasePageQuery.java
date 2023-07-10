package com.bobo.bshop.component.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author hua.bin
 * @version 1.0.0
 * @ClassName BaseQuery.java
 * @Description 公共查询
 * @createTime 2022-05-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePageQuery {

  private Integer pageSize;

  private Integer pageNum;


  public Integer getPageSize() {
    return Objects.isNull(pageSize) ? 1 : Math.max(1, pageSize);
  }

  public Integer getPageNum() {
    return Objects.nonNull(pageNum) ? Math.min(100, Math.max(1, pageNum)) : 10;
  }

}