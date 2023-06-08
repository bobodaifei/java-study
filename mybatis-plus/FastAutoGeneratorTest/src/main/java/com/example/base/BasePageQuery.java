package com.example.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author hua.bin
 * @version 1.0.0
 * @ClassName BaseQuery.java
 * @Description 公共查询
 * @createTime 2022-05-13
 */
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class BasePageQuery {

  @NotNull(message = "页码不能为空")
  @Min(1)
  @ApiModelProperty("页码")
  private Integer currentPage;

  @NotNull(message = "分页size不能为空")
  @ApiModelProperty("分页size")
  private Integer currentSize;


  public Integer getCurrentPage() {
    return Objects.isNull(currentPage) ? 1 : Math.max(1, currentPage);
  }

  public Integer getCurrentSize() {
    return Objects.nonNull(currentSize) ? Math.min(100, Math.max(1, currentSize)) : 10;
  }

}